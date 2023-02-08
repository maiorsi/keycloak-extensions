package maiorsi.keycloak.ldap;

import java.util.regex.Pattern;
import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.ldap.LDAPStorageProvider;
import org.keycloak.storage.ldap.idm.model.LDAPObject;
import org.keycloak.storage.ldap.idm.query.internal.LDAPQuery;
import org.keycloak.storage.ldap.mappers.AbstractLDAPStorageMapper;

public final class LdapShortUpnStorageMapper extends AbstractLDAPStorageMapper {
    private static final Logger log = Logger.getLogger(LdapShortUpnStorageMapper.class);

    private static final String LDAP_ATTRIBUTE = "userPrincipalName";
    private static final String SHORT_UPN_PATTERN = "(?<username>\\w+)@(?<domain>\\w+)(?:[\\w.]+)";
    private static final String DEFAULT_USER_MODEL_ATTRIBUTE = "logon";
    private static final String SHORT_UPN_REPLACEMENT_FILTER = "$1@$2";
    static final String USER_MODEL_ATTRIBUTE = "user.model.attribute";
    static final String DOMAIN = "config.domain";

    public LdapShortUpnStorageMapper(ComponentModel mapperModel, LDAPStorageProvider ldapProvider) {
        super(mapperModel, ldapProvider);
    }

    @Override
    public void beforeLDAPQuery(LDAPQuery query) {
        query.addReturningLdapAttribute(LDAP_ATTRIBUTE);
        query.addReturningReadOnlyLdapAttribute(LDAP_ATTRIBUTE);
    }

    @Override
    public void onImportUserFromLDAP(LDAPObject ldapUser, UserModel userModel, RealmModel _realm, boolean _isCreate) {
        try {
            String upnAttributeValue = ldapUser.getAttributeAsString(LDAP_ATTRIBUTE);

            log.tracef("Ldap User %s", ldapUser);
            log.tracef("Ldap UPN Attribute Value '%s'", upnAttributeValue);

            if (upnAttributeValue != null && !upnAttributeValue.trim().isEmpty()) {
                String shortUpn = Pattern.compile(SHORT_UPN_PATTERN)
                        .matcher(upnAttributeValue)
                        .replaceAll(SHORT_UPN_REPLACEMENT_FILTER)
                        .toLowerCase();

                userModel.setSingleAttribute(getUserModelAttribute(), shortUpn);

                log.debugf("Setting user %s short-upn to %s", ldapUser.getUuid(), shortUpn);
            } else {
                // Fall-back in case a user does not have a UPN (old account)
                String shortUpn = getDomain().toUpperCase() + "\\"
                        + userModel.getUsername().toLowerCase();

                userModel.setSingleAttribute(getUserModelAttribute(), shortUpn);

                log.debugf("[Fall-back]: Setting user %s short-upn to %s", ldapUser.getUuid(), shortUpn);
            }
        } catch (Exception exception) {
            log.warnf("Failed to set short-upn for user %s; %s", ldapUser.getUuid(), exception.getMessage());
        }
    }

    @Override
    public void onRegisterUserToLDAP(LDAPObject _ldapUser, UserModel _userModel, RealmModel _realm) {}

    @Override
    public UserModel proxy(LDAPObject _ldapUser, UserModel userModel, RealmModel _realm) {
        return userModel;
    }

    private String getDomain() {
        return this.mapperModel.getConfig().getFirst(DOMAIN);
    }

    private String getUserModelAttribute() {
        String userModelLogonAttributeName = this.mapperModel.getConfig().getFirst(USER_MODEL_ATTRIBUTE);
        return userModelLogonAttributeName == null ? DEFAULT_USER_MODEL_ATTRIBUTE : userModelLogonAttributeName;
    }
}
