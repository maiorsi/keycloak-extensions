package maiorsi.keycloak.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link UserDto}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new UserDto.Builder()}.
 */
@Generated(from = "UserDto", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
public final class ImmutableUserDto extends UserDto {
  private final String id;
  private final String username;
  private final @Nullable String firstName;
  private final @Nullable String lastName;
  private final @Nullable String email;
  private final @Nullable String distinguishedName;
  private final @Nullable String guid;
  private final @Nullable String userPrincipalName;
  private final ImmutableList<String> roles;

  private ImmutableUserDto(
      String id,
      String username,
      @Nullable String firstName,
      @Nullable String lastName,
      @Nullable String email,
      @Nullable String distinguishedName,
      @Nullable String guid,
      @Nullable String userPrincipalName,
      ImmutableList<String> roles) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.distinguishedName = distinguishedName;
    this.guid = guid;
    this.userPrincipalName = userPrincipalName;
    this.roles = roles;
  }

  /**
   * @return The value of the {@code id} attribute
   */
  @JsonProperty("id")
  @Override
  public String id() {
    return id;
  }

  /**
   * @return The value of the {@code username} attribute
   */
  @JsonProperty("username")
  @Override
  public String username() {
    return username;
  }

  /**
   * @return The value of the {@code firstName} attribute
   */
  @JsonProperty("firstName")
  @JsonIgnore
  @Override
  public Optional<String> firstName() {
    return Optional.ofNullable(firstName);
  }

  /**
   * @return The value of the {@code lastName} attribute
   */
  @JsonProperty("lastName")
  @JsonIgnore
  @Override
  public Optional<String> lastName() {
    return Optional.ofNullable(lastName);
  }

  /**
   * @return The value of the {@code email} attribute
   */
  @JsonProperty("email")
  @JsonIgnore
  @Override
  public Optional<String> email() {
    return Optional.ofNullable(email);
  }

  /**
   * @return The value of the {@code distinguishedName} attribute
   */
  @JsonProperty("distinguishedName")
  @JsonIgnore
  @Override
  public Optional<String> distinguishedName() {
    return Optional.ofNullable(distinguishedName);
  }

  /**
   * @return The value of the {@code guid} attribute
   */
  @JsonProperty("guid")
  @JsonIgnore
  @Override
  public Optional<String> guid() {
    return Optional.ofNullable(guid);
  }

  /**
   * @return The value of the {@code userPrincipalName} attribute
   */
  @JsonProperty("userPrincipalName")
  @JsonIgnore
  @Override
  public Optional<String> userPrincipalName() {
    return Optional.ofNullable(userPrincipalName);
  }

  /**
   * @return The value of the {@code roles} attribute
   */
  @JsonProperty("roles")
  @Override
  public ImmutableList<String> roles() {
    return roles;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link UserDto#id() id} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for id
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableUserDto withId(String value) {
    String newValue = Objects.requireNonNull(value, "id");
    if (this.id.equals(newValue)) return this;
    return new ImmutableUserDto(
        newValue,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link UserDto#username() username} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for username
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableUserDto withUsername(String value) {
    String newValue = Objects.requireNonNull(value, "username");
    if (this.username.equals(newValue)) return this;
    return new ImmutableUserDto(
        this.id,
        newValue,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting a <i>present</i> value for the optional {@link UserDto#firstName() firstName} attribute.
   * @param value The value for firstName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withFirstName(String value) {
    @Nullable String newValue = Objects.requireNonNull(value, "firstName");
    if (Objects.equals(this.firstName, newValue)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        newValue,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting an optional value for the {@link UserDto#firstName() firstName} attribute.
   * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
   * @param optional A value for firstName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withFirstName(Optional<String> optional) {
    @Nullable String value = optional.orElse(null);
    if (Objects.equals(this.firstName, value)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        value,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting a <i>present</i> value for the optional {@link UserDto#lastName() lastName} attribute.
   * @param value The value for lastName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withLastName(String value) {
    @Nullable String newValue = Objects.requireNonNull(value, "lastName");
    if (Objects.equals(this.lastName, newValue)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        newValue,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting an optional value for the {@link UserDto#lastName() lastName} attribute.
   * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
   * @param optional A value for lastName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withLastName(Optional<String> optional) {
    @Nullable String value = optional.orElse(null);
    if (Objects.equals(this.lastName, value)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        value,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting a <i>present</i> value for the optional {@link UserDto#email() email} attribute.
   * @param value The value for email
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withEmail(String value) {
    @Nullable String newValue = Objects.requireNonNull(value, "email");
    if (Objects.equals(this.email, newValue)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        newValue,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting an optional value for the {@link UserDto#email() email} attribute.
   * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
   * @param optional A value for email
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withEmail(Optional<String> optional) {
    @Nullable String value = optional.orElse(null);
    if (Objects.equals(this.email, value)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        value,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting a <i>present</i> value for the optional {@link UserDto#distinguishedName() distinguishedName} attribute.
   * @param value The value for distinguishedName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withDistinguishedName(String value) {
    @Nullable String newValue = Objects.requireNonNull(value, "distinguishedName");
    if (Objects.equals(this.distinguishedName, newValue)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        newValue,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting an optional value for the {@link UserDto#distinguishedName() distinguishedName} attribute.
   * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
   * @param optional A value for distinguishedName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withDistinguishedName(Optional<String> optional) {
    @Nullable String value = optional.orElse(null);
    if (Objects.equals(this.distinguishedName, value)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        value,
        this.guid,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting a <i>present</i> value for the optional {@link UserDto#guid() guid} attribute.
   * @param value The value for guid
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withGuid(String value) {
    @Nullable String newValue = Objects.requireNonNull(value, "guid");
    if (Objects.equals(this.guid, newValue)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        newValue,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting an optional value for the {@link UserDto#guid() guid} attribute.
   * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
   * @param optional A value for guid
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withGuid(Optional<String> optional) {
    @Nullable String value = optional.orElse(null);
    if (Objects.equals(this.guid, value)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        value,
        this.userPrincipalName,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting a <i>present</i> value for the optional {@link UserDto#userPrincipalName() userPrincipalName} attribute.
   * @param value The value for userPrincipalName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withUserPrincipalName(String value) {
    @Nullable String newValue = Objects.requireNonNull(value, "userPrincipalName");
    if (Objects.equals(this.userPrincipalName, newValue)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        newValue,
        this.roles);
  }

  /**
   * Copy the current immutable object by setting an optional value for the {@link UserDto#userPrincipalName() userPrincipalName} attribute.
   * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
   * @param optional A value for userPrincipalName
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withUserPrincipalName(Optional<String> optional) {
    @Nullable String value = optional.orElse(null);
    if (Objects.equals(this.userPrincipalName, value)) return this;
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        value,
        this.roles);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link UserDto#roles() roles}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withRoles(String... elements) {
    ImmutableList<String> newValue = ImmutableList.copyOf(elements);
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        newValue);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link UserDto#roles() roles}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of roles elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableUserDto withRoles(Iterable<String> elements) {
    if (this.roles == elements) return this;
    ImmutableList<String> newValue = ImmutableList.copyOf(elements);
    return new ImmutableUserDto(
        this.id,
        this.username,
        this.firstName,
        this.lastName,
        this.email,
        this.distinguishedName,
        this.guid,
        this.userPrincipalName,
        newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableUserDto} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableUserDto
        && equalTo(0, (ImmutableUserDto) another);
  }

  private boolean equalTo(int synthetic, ImmutableUserDto another) {
    return id.equals(another.id)
        && username.equals(another.username)
        && Objects.equals(firstName, another.firstName)
        && Objects.equals(lastName, another.lastName)
        && Objects.equals(email, another.email)
        && Objects.equals(distinguishedName, another.distinguishedName)
        && Objects.equals(guid, another.guid)
        && Objects.equals(userPrincipalName, another.userPrincipalName)
        && roles.equals(another.roles);
  }

  /**
   * Computes a hash code from attributes: {@code id}, {@code username}, {@code firstName}, {@code lastName}, {@code email}, {@code distinguishedName}, {@code guid}, {@code userPrincipalName}, {@code roles}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + id.hashCode();
    h += (h << 5) + username.hashCode();
    h += (h << 5) + Objects.hashCode(firstName);
    h += (h << 5) + Objects.hashCode(lastName);
    h += (h << 5) + Objects.hashCode(email);
    h += (h << 5) + Objects.hashCode(distinguishedName);
    h += (h << 5) + Objects.hashCode(guid);
    h += (h << 5) + Objects.hashCode(userPrincipalName);
    h += (h << 5) + roles.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code UserDto} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("UserDto")
        .omitNullValues()
        .add("id", id)
        .add("username", username)
        .add("firstName", firstName)
        .add("lastName", lastName)
        .add("email", email)
        .add("distinguishedName", distinguishedName)
        .add("guid", guid)
        .add("userPrincipalName", userPrincipalName)
        .add("roles", roles)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "UserDto", generator = "Immutables")
  @Deprecated
  @SuppressWarnings("Immutable")
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json extends UserDto {
    @Nullable String id;
    @Nullable String username;
    @Nullable Optional<String> firstName = Optional.empty();
    @Nullable Optional<String> lastName = Optional.empty();
    @Nullable Optional<String> email = Optional.empty();
    @Nullable Optional<String> distinguishedName = Optional.empty();
    @Nullable Optional<String> guid = Optional.empty();
    @Nullable Optional<String> userPrincipalName = Optional.empty();
    @Nullable List<String> roles = ImmutableList.of();
    @JsonProperty("id")
    public void setId(String id) {
      this.id = id;
    }
    @JsonProperty("username")
    public void setUsername(String username) {
      this.username = username;
    }
    @JsonProperty("firstName")
    @JsonIgnore
    public void setFirstName(Optional<String> firstName) {
      this.firstName = firstName;
    }
    @JsonProperty("lastName")
    @JsonIgnore
    public void setLastName(Optional<String> lastName) {
      this.lastName = lastName;
    }
    @JsonProperty("email")
    @JsonIgnore
    public void setEmail(Optional<String> email) {
      this.email = email;
    }
    @JsonProperty("distinguishedName")
    @JsonIgnore
    public void setDistinguishedName(Optional<String> distinguishedName) {
      this.distinguishedName = distinguishedName;
    }
    @JsonProperty("guid")
    @JsonIgnore
    public void setGuid(Optional<String> guid) {
      this.guid = guid;
    }
    @JsonProperty("userPrincipalName")
    @JsonIgnore
    public void setUserPrincipalName(Optional<String> userPrincipalName) {
      this.userPrincipalName = userPrincipalName;
    }
    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
      this.roles = roles;
    }
    @Override
    public String id() { throw new UnsupportedOperationException(); }
    @Override
    public String username() { throw new UnsupportedOperationException(); }
    @Override
    public Optional<String> firstName() { throw new UnsupportedOperationException(); }
    @Override
    public Optional<String> lastName() { throw new UnsupportedOperationException(); }
    @Override
    public Optional<String> email() { throw new UnsupportedOperationException(); }
    @Override
    public Optional<String> distinguishedName() { throw new UnsupportedOperationException(); }
    @Override
    public Optional<String> guid() { throw new UnsupportedOperationException(); }
    @Override
    public Optional<String> userPrincipalName() { throw new UnsupportedOperationException(); }
    @Override
    public List<String> roles() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableUserDto fromJson(Json json) {
    UserDto.Builder builder = new UserDto.Builder();
    if (json.id != null) {
      builder.id(json.id);
    }
    if (json.username != null) {
      builder.username(json.username);
    }
    if (json.firstName != null) {
      builder.firstName(json.firstName);
    }
    if (json.lastName != null) {
      builder.lastName(json.lastName);
    }
    if (json.email != null) {
      builder.email(json.email);
    }
    if (json.distinguishedName != null) {
      builder.distinguishedName(json.distinguishedName);
    }
    if (json.guid != null) {
      builder.guid(json.guid);
    }
    if (json.userPrincipalName != null) {
      builder.userPrincipalName(json.userPrincipalName);
    }
    if (json.roles != null) {
      builder.addAllRoles(json.roles);
    }
    return (ImmutableUserDto) builder.build();
  }

  /**
   * Creates an immutable copy of a {@link UserDto} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable UserDto instance
   */
  public static ImmutableUserDto copyOf(UserDto instance) {
    if (instance instanceof ImmutableUserDto) {
      return (ImmutableUserDto) instance;
    }
    return new UserDto.Builder()
        .from(instance)
        .build();
  }

  /**
   * Builds instances of type {@link ImmutableUserDto ImmutableUserDto}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "UserDto", generator = "Immutables")
  @NotThreadSafe
  public static class Builder {
    private static final long INIT_BIT_ID = 0x1L;
    private static final long INIT_BIT_USERNAME = 0x2L;
    private long initBits = 0x3L;

    private @Nullable String id;
    private @Nullable String username;
    private @Nullable String firstName;
    private @Nullable String lastName;
    private @Nullable String email;
    private @Nullable String distinguishedName;
    private @Nullable String guid;
    private @Nullable String userPrincipalName;
    private ImmutableList.Builder<String> roles = ImmutableList.builder();

    /**
     * Creates a builder for {@link ImmutableUserDto ImmutableUserDto} instances.
     * <pre>
     * new UserDto.Builder()
     *    .id(String) // required {@link UserDto#id() id}
     *    .username(String) // required {@link UserDto#username() username}
     *    .firstName(String) // optional {@link UserDto#firstName() firstName}
     *    .lastName(String) // optional {@link UserDto#lastName() lastName}
     *    .email(String) // optional {@link UserDto#email() email}
     *    .distinguishedName(String) // optional {@link UserDto#distinguishedName() distinguishedName}
     *    .guid(String) // optional {@link UserDto#guid() guid}
     *    .userPrincipalName(String) // optional {@link UserDto#userPrincipalName() userPrincipalName}
     *    .addRoles|addAllRoles(String) // {@link UserDto#roles() roles} elements
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof UserDto.Builder)) {
        throw new UnsupportedOperationException("Use: new UserDto.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code UserDto} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * Collection elements and entries will be added, not replaced.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder from(UserDto instance) {
      Objects.requireNonNull(instance, "instance");
      id(instance.id());
      username(instance.username());
      Optional<String> firstNameOptional = instance.firstName();
      if (firstNameOptional.isPresent()) {
        firstName(firstNameOptional);
      }
      Optional<String> lastNameOptional = instance.lastName();
      if (lastNameOptional.isPresent()) {
        lastName(lastNameOptional);
      }
      Optional<String> emailOptional = instance.email();
      if (emailOptional.isPresent()) {
        email(emailOptional);
      }
      Optional<String> distinguishedNameOptional = instance.distinguishedName();
      if (distinguishedNameOptional.isPresent()) {
        distinguishedName(distinguishedNameOptional);
      }
      Optional<String> guidOptional = instance.guid();
      if (guidOptional.isPresent()) {
        guid(guidOptional);
      }
      Optional<String> userPrincipalNameOptional = instance.userPrincipalName();
      if (userPrincipalNameOptional.isPresent()) {
        userPrincipalName(userPrincipalNameOptional);
      }
      addAllRoles(instance.roles());
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserDto#id() id} attribute.
     * @param id The value for id 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("id")
    public final UserDto.Builder id(String id) {
      this.id = Objects.requireNonNull(id, "id");
      initBits &= ~INIT_BIT_ID;
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserDto#username() username} attribute.
     * @param username The value for username 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("username")
    public final UserDto.Builder username(String username) {
      this.username = Objects.requireNonNull(username, "username");
      initBits &= ~INIT_BIT_USERNAME;
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#firstName() firstName} to firstName.
     * @param firstName The value for firstName
     * @return {@code this} builder for chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder firstName(String firstName) {
      this.firstName = Objects.requireNonNull(firstName, "firstName");
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#firstName() firstName} to firstName.
     * @param firstName The value for firstName
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("firstName")
    @JsonIgnore
    public final UserDto.Builder firstName(Optional<String> firstName) {
      this.firstName = firstName.orElse(null);
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#lastName() lastName} to lastName.
     * @param lastName The value for lastName
     * @return {@code this} builder for chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder lastName(String lastName) {
      this.lastName = Objects.requireNonNull(lastName, "lastName");
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#lastName() lastName} to lastName.
     * @param lastName The value for lastName
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("lastName")
    @JsonIgnore
    public final UserDto.Builder lastName(Optional<String> lastName) {
      this.lastName = lastName.orElse(null);
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#email() email} to email.
     * @param email The value for email
     * @return {@code this} builder for chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder email(String email) {
      this.email = Objects.requireNonNull(email, "email");
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#email() email} to email.
     * @param email The value for email
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("email")
    @JsonIgnore
    public final UserDto.Builder email(Optional<String> email) {
      this.email = email.orElse(null);
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#distinguishedName() distinguishedName} to distinguishedName.
     * @param distinguishedName The value for distinguishedName
     * @return {@code this} builder for chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder distinguishedName(String distinguishedName) {
      this.distinguishedName = Objects.requireNonNull(distinguishedName, "distinguishedName");
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#distinguishedName() distinguishedName} to distinguishedName.
     * @param distinguishedName The value for distinguishedName
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("distinguishedName")
    @JsonIgnore
    public final UserDto.Builder distinguishedName(Optional<String> distinguishedName) {
      this.distinguishedName = distinguishedName.orElse(null);
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#guid() guid} to guid.
     * @param guid The value for guid
     * @return {@code this} builder for chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder guid(String guid) {
      this.guid = Objects.requireNonNull(guid, "guid");
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#guid() guid} to guid.
     * @param guid The value for guid
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("guid")
    @JsonIgnore
    public final UserDto.Builder guid(Optional<String> guid) {
      this.guid = guid.orElse(null);
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#userPrincipalName() userPrincipalName} to userPrincipalName.
     * @param userPrincipalName The value for userPrincipalName
     * @return {@code this} builder for chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder userPrincipalName(String userPrincipalName) {
      this.userPrincipalName = Objects.requireNonNull(userPrincipalName, "userPrincipalName");
      return (UserDto.Builder) this;
    }

    /**
     * Initializes the optional value {@link UserDto#userPrincipalName() userPrincipalName} to userPrincipalName.
     * @param userPrincipalName The value for userPrincipalName
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("userPrincipalName")
    @JsonIgnore
    public final UserDto.Builder userPrincipalName(Optional<String> userPrincipalName) {
      this.userPrincipalName = userPrincipalName.orElse(null);
      return (UserDto.Builder) this;
    }

    /**
     * Adds one element to {@link UserDto#roles() roles} list.
     * @param element A roles element
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder addRoles(String element) {
      this.roles.add(element);
      return (UserDto.Builder) this;
    }

    /**
     * Adds elements to {@link UserDto#roles() roles} list.
     * @param elements An array of roles elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder addRoles(String... elements) {
      this.roles.add(elements);
      return (UserDto.Builder) this;
    }


    /**
     * Sets or replaces all elements for {@link UserDto#roles() roles} list.
     * @param elements An iterable of roles elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("roles")
    public final UserDto.Builder roles(Iterable<String> elements) {
      this.roles = ImmutableList.builder();
      return addAllRoles(elements);
    }

    /**
     * Adds elements to {@link UserDto#roles() roles} list.
     * @param elements An iterable of roles elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final UserDto.Builder addAllRoles(Iterable<String> elements) {
      this.roles.addAll(elements);
      return (UserDto.Builder) this;
    }

    /**
     * Builds a new {@link ImmutableUserDto ImmutableUserDto}.
     * @return An immutable instance of UserDto
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableUserDto build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableUserDto(
          id,
          username,
          firstName,
          lastName,
          email,
          distinguishedName,
          guid,
          userPrincipalName,
          roles.build());
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_ID) != 0) attributes.add("id");
      if ((initBits & INIT_BIT_USERNAME) != 0) attributes.add("username");
      return "Cannot build UserDto, some of required attributes are not set " + attributes;
    }
  }
}
