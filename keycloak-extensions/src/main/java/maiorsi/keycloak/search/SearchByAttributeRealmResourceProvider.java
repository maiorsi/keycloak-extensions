package maiorsi.keycloak.search;

import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import maiorsi.keycloak.mapping.UserMapper;
import maiorsi.keycloak.model.UserDto;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.ClientModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

public final class SearchByAttributeRealmResourceProvider implements RealmResourceProvider {
    private static final Logger log = Logger.getLogger(SearchByAttributeRealmResourceProvider.class);
    private static final String DEFAULT_ATTR = "LDAP_ID";

    private final KeycloakSession session;
    private final UserMapper userMapper;

    public SearchByAttributeRealmResourceProvider(KeycloakSession session) {
        this.session = session;
        this.userMapper = new UserMapper();
    }

    @Override
    public void close() {}

    @Override
    public Object getResource() {
        return this;
    }

    /**
     * Simple Test Endpoint.
     */
    @GET
    @NoCache
    @Produces("text/plain; charset=utf-8")
    @Encoded
    public String get() {
        return "search-by-attr available!";
    }

    /**
     * Search for users by custom attribute.
     *
     * @param attr  - Attribute to search - defaults to LDAP_ID
     * @param first - Paging token
     * @param limit - Max number of results to return
     * @param query - Search query
     * @return - A list of users
     */
    @GET
    @Path("users")
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    @Encoded
    public List<UserDto> searchUsersByAttribute(
            @DefaultValue(DEFAULT_ATTR) @QueryParam("attr") String attr,
            @DefaultValue("0") @QueryParam("first") Long first,
            @DefaultValue("10") @QueryParam("max") Long limit,
            @QueryParam("query") String query) {
        log.infof("searchUsersByAttribute(%s,%s,%s,%s)", attr, first, limit, query);

        return session.users()
                .searchForUserByUserAttributeStream(session.getContext().getRealm(), attr, query)
                .skip(first)
                .limit(limit)
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    /**
     * Search for users by attribute - include roles for a specific client.
     *
     * @param attr     - Attribute to search - defaults to LDAP_ID
     * @param first    - Paging token
     * @param limit    - Max number of results to return
     * @param query    - Search query
     * @param clientId - Client Id (text) of client to fetcdh roles fole
     * @return - A list of users
     */
    @GET
    @Path("users-and-roles")
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    @Encoded
    public List<UserDto> searchUsersByAttributeWithRoles(
            @DefaultValue(DEFAULT_ATTR) @QueryParam("attr") String attr,
            @DefaultValue("0") @QueryParam("first") Long first,
            @DefaultValue("10") @QueryParam("max") Long limit,
            @QueryParam("query") String query,
            @QueryParam("client_id") String clientId) {
        log.infof("searchUsersByAttributeWithRoles(%s,%s,%s,%s,%s)", attr, first, limit, query, clientId);

        final ClientModel client =
                session.clients().getClientByClientId(session.getContext().getRealm(), clientId);

        return session.users()
                .searchForUserByUserAttributeStream(session.getContext().getRealm(), attr, query)
                .skip(first)
                .limit(limit)
                .map(user -> userMapper.mapToUserDtoWithRoles(user, client))
                .collect(Collectors.toList());
    }
}
