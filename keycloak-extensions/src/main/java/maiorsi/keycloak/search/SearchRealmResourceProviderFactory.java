package maiorsi.keycloak.search;

import org.jboss.logging.Logger;
import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public final class SearchRealmResourceProviderFactory implements RealmResourceProviderFactory {
    private static final Logger log = Logger.getLogger(SearchRealmResourceProviderFactory.class);
    private static final String PROVIDER_ID = "search";

    public SearchRealmResourceProviderFactory() {
        log.info("Initialising...");
    }

    @Override
    public RealmResourceProvider create(KeycloakSession session) {
        RealmResourceProvider provider = new SearchRealmResourceProvider(session);

        log.info("Initialised.");

        return provider;
    }

    @Override
    public void init(Scope _config) {}

    @Override
    public void postInit(KeycloakSessionFactory _factory) {}

    @Override
    public void close() {}

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
