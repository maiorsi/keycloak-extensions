package maiorsi.keycloak.ldap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.ldap.LDAPStorageProvider;
import org.keycloak.storage.ldap.idm.model.LDAPObject;
import org.keycloak.storage.ldap.idm.query.internal.LDAPQuery;
import org.keycloak.storage.ldap.mappers.AbstractLDAPStorageMapper;

public final class LdapLogonStorageMapper extends AbstractLDAPStorageMapper {
    private static final Logger log = Logger.getLogger(LdapLogonStorageMapper.class);

    private static final String LDAP_ATTRIBUTE = "userPrincipalName";
    private static final String SHORT_UPN_PATTERN = "(?<username>\\w+)@(?<domain>\\w+)(?:[\\w.]+)";
    private static final String DEFAULT_USER_MODEL_ATTRIBUTE = "logon";
    static final String USER_MODEL_ATTRIBUTE = "user.model.attribute";
    static final String DOMAIN = "config.domain";

    public LdapLogonStorageMapper(ComponentModel mapperModel, LDAPStorageProvider ldapProvider) {
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
                Matcher matcher = Pattern.compile(SHORT_UPN_PATTERN).matcher(upnAttributeValue);

                while (matcher.find()) {
                    String username = matcher.group(1).toLowerCase();
                    String domain = matcher.group(2).toUpperCase();
                    String logon = domain + "\\" + username;

                    userModel.setSingleAttribute(getUserModelAttribute(), logon);

                    log.debugf("Setting user %s logon to %s", ldapUser.getUuid(), logon);
                }
            } else {
                // Fall-back in case a user does not have a UPN (old account)
                String logon = getDomain().toUpperCase() + "\\"
                        + userModel.getUsername().toLowerCase();

                userModel.setSingleAttribute(getUserModelAttribute(), logon);

                log.debugf("[Fall-back]: Setting user %s logon to %s", ldapUser.getUuid(), logon);
            }
        } catch (Exception exception) {
            log.warnf("Failed to set logon for user %s; %s", ldapUser.getUuid(), exception.getMessage());
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
