package maiorsi.keycloak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableUserDto.class)
@JsonDeserialize(as = ImmutableUserDto.class)
@JsonInclude(Include.ALWAYS)
public abstract class UserDto {
    public abstract String id();

    public abstract String username();

    @JsonIgnore
    public abstract Optional<String> firstName();

    @JsonProperty("firstName")
    public final String firstNameStr() {
        return firstName().orElse(null);
    }

    @JsonIgnore
    public abstract Optional<String> lastName();

    @JsonProperty("lastName")
    public final String lastNameStr() {
        return lastName().orElse(null);
    }

    @JsonIgnore
    public abstract Optional<String> email();

    @JsonProperty("email")
    public final String emailStr() {
        return email().orElse(null);
    }

    @JsonIgnore
    public abstract Optional<String> distinguishedName();

    @JsonProperty("distinguishedName")
    public final String distinguishedNameStr() {
        return distinguishedName().orElse(null);
    }

    @JsonIgnore
    public abstract Optional<String> guid();

    @JsonProperty("guid")
    public final String guidStr() {
        return guid().orElse(null);
    }

    @JsonIgnore
    public abstract Optional<String> userPrincipalName();

    @JsonProperty("userPrincipalName")
    public final String userPrincipalNameStr() {
        return userPrincipalName().orElse(null);
    }

    public abstract List<String> roles();

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImmutableUserDto.Builder {}
}