package maiorsi.keycloak.ldap;

import java.util.ArrayList;
import java.util.List;
import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.storage.ldap.LDAPStorageProvider;
import org.keycloak.storage.ldap.mappers.AbstractLDAPStorageMapper;
import org.keycloak.storage.ldap.mappers.AbstractLDAPStorageMapperFactory;

public final class LdapShortUpnStorageMapperFactory extends AbstractLDAPStorageMapperFactory {
    private static final String PROVIDER_ID = "short-upn-ldap-mapper";
    private static final List<ProviderConfigProperty> configProperties = new ArrayList<>();

    public LdapShortUpnStorageMapperFactory() {}

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    protected AbstractLDAPStorageMapper createMapper(
            ComponentModel mapperModel, LDAPStorageProvider federationProvider) {
        return new LdapShortUpnStorageMapper(mapperModel, federationProvider);
    }

    @Override
    public String getHelpText() {
        return "Used to map a single 'logon' attribute with the form DOMAIN\\username to a user model.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override
    public void validateConfiguration(KeycloakSession _session, RealmModel _realm, ComponentModel config)
            throws ComponentValidationException {
        this.checkMandatoryConfigAttribute(LdapLogonStorageMapper.USER_MODEL_ATTRIBUTE, "User Model Attribute", config);
        this.checkMandatoryConfigAttribute(LdapLogonStorageMapper.DOMAIN, "Domain", config);
    }

    static {
        ProviderConfigProperty attrUserModel = createConfigProperty(
                LdapLogonStorageMapper.USER_MODEL_ATTRIBUTE,
                "User Model Attribute Name",
                "Name of the User attribute containing logon - default 'logon'",
                "String",
                null);

        ProviderConfigProperty attrDomain = createConfigProperty(
                LdapLogonStorageMapper.DOMAIN,
                "Domain",
                "LDAP Domain; Upper case short domain -> EXAMPLE",
                "String",
                null);

        configProperties.add(attrUserModel);
        configProperties.add(attrDomain);
    }
}
