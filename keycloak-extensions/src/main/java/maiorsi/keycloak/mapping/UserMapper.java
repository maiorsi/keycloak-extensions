package maiorsi.keycloak.mapping;

import com.google.common.collect.ImmutableList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import maiorsi.keycloak.model.UserDto;
import maiorsi.keycloak.util.LdapUtils;
import org.jboss.logging.Logger;
import org.keycloak.models.ClientModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserModel;

public final class UserMapper {
    private static final Logger log = Logger.getLogger(UserMapper.class);

    public UserDto defaultMapping(UserModel userModel) {
        Optional<String> objectGuid = Optional.ofNullable(userModel.getFirstAttribute("objectGuidBase64"));
        Optional<String> decodedGuid;

        if (objectGuid.isPresent()) {
            byte[] bytes = Base64.getDecoder().decode(objectGuid.get());
            decodedGuid = Optional.ofNullable(LdapUtils.decodeObjectGuid(bytes));
        } else {
            decodedGuid = Optional.ofNullable(userModel.getFirstAttribute("LDAP_ID"));
        }

        Optional<String> objectSid = Optional.ofNullable(userModel.getFirstAttribute("objectSidBase64"));
        Optional<String> decodedSid = Optional.empty();

        if (objectSid.isPresent()) {
            byte[] bytes = Base64.getDecoder().decode(objectSid.get());
            decodedSid = Optional.of(LdapUtils.decodeObjectSid(bytes));
        }

        return UserDto.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .guid(decodedGuid)
                .sid(decodedSid)
                .distinguishedName(Optional.ofNullable(userModel.getFirstAttribute("LDAP_ENTRY_DN")))
                .userPrincipalName(Optional.ofNullable(userModel.getFirstAttribute("userPrincipalName")))
                .roles(ImmutableList.of())
                .build();
    }

    public UserDto mapToUserDto(UserModel userModel) {
        return UserDto.builder().from(defaultMapping(userModel)).build();
    }

    public UserDto mapToUserDtoWithRoles(UserModel userModel, ClientModel client) {
        log.debugf("Checking roles for %s against client %s", userModel.getUsername(), client.getClientId());

        List<String> roles = client.getRolesStream()
                .filter(userModel::hasRole)
                .map(RoleModel::getName)
                .collect(Collectors.toList());

        return UserDto.builder().from(defaultMapping(userModel)).roles(roles).build();
    }
}
