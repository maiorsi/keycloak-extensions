package maiorsi.keycloak.ldap;

import java.util.Base64;
import java.util.Iterator;
import maiorsi.keycloak.util.LdapUtils;
import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.ldap.LDAPStorageProvider;
import org.keycloak.storage.ldap.idm.model.LDAPObject;
import org.keycloak.storage.ldap.idm.query.Condition;
import org.keycloak.storage.ldap.idm.query.internal.LDAPQuery;
import org.keycloak.storage.ldap.mappers.AbstractLDAPStorageMapper;

public final class LdapSidStorageMapper extends AbstractLDAPStorageMapper {
    private static final Logger log = Logger.getLogger(LdapSidStorageMapper.class);

    private static final String LDAP_ATTRIBUTE = "objectSid";
    private static final String DEFAULT_USER_MODEL_ATTRIBUTE = "sid";
    private static final String USER_MODEL_BASE64_ATTRIBUTE = "objectSidBase64";

    static final String USER_MODEL_ATTRIBUTE = "user.model.attribute";

    public LdapSidStorageMapper(ComponentModel mapperModel, LDAPStorageProvider ldapProvider) {
        super(mapperModel, ldapProvider);
    }

    @Override
    public void beforeLDAPQuery(LDAPQuery query) {
        query.addReturningLdapAttribute(LDAP_ATTRIBUTE);
        query.addReturningReadOnlyLdapAttribute(LDAP_ATTRIBUTE);

        Iterator<Condition> iterator = query.getConditions();

        while (true) {
            Condition condition;
            String parameterName;

            do {
                do {
                    if (!iterator.hasNext()) {
                        return;
                    }

                    condition = iterator.next();

                    condition.updateParameterName(USER_MODEL_BASE64_ATTRIBUTE, LDAP_ATTRIBUTE);
                    parameterName = condition.getParameterName();
                } while (parameterName == null);
            } while (!parameterName.equalsIgnoreCase(USER_MODEL_BASE64_ATTRIBUTE)
                    && !parameterName.equalsIgnoreCase(LDAP_ATTRIBUTE));

            condition.setBinary(true);
        }
    }

    @Override
    public void onImportUserFromLDAP(LDAPObject ldapUser, UserModel userModel, RealmModel _realm, boolean _isCreate) {
        String sidBase64 = ldapUser.getAttributeAsString(LDAP_ATTRIBUTE);

        log.tracef("Ldap User %s", ldapUser);
        log.tracef("Ldap SId attribute value '%s'", sidBase64);

        if (sidBase64 != null && !sidBase64.trim().isEmpty()) {
            byte[] bytes = Base64.getDecoder().decode(sidBase64);
            String sid = LdapUtils.decodeObjectSid(bytes);

            userModel.setSingleAttribute(getUserModelAttribute(), sid);

            log.debugf("Setting user %s sid to %s", ldapUser.getUuid(), sid);
        } else {
            log.warnf("Failed to set sid for user %s; %s", ldapUser.getUuid(), sidBase64);
        }
    }

    @Override
    public void onRegisterUserToLDAP(LDAPObject _ldapUser, UserModel _userModel, RealmModel _realm) {}

    @Override
    public UserModel proxy(LDAPObject _ldapUser, UserModel userModel, RealmModel _realm) {
        return userModel;
    }

    private String getUserModelAttribute() {
        String userModelLogonAttributeName = this.mapperModel.getConfig().getFirst(USER_MODEL_ATTRIBUTE);
        return userModelLogonAttributeName == null ? DEFAULT_USER_MODEL_ATTRIBUTE : userModelLogonAttributeName;
    }
}
