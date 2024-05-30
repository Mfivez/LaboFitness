package be.labofitness.labo_fitness.bll.model.login;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the response model for user login.
 * <p>
 * This class encapsulates the user's ID, login email, roles, and authentication token.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Long id = 123L;
 * String login = "user@example.com";
 * Set<String> roles = Set.of("USER", "ADMIN");
 * String token = "authentication_token";
 * UserLoginResponse response = new UserLoginResponse(id, login, roles, token);
 * }</pre>
 */
@Data
public class UserLoginResponse {
    private Long id;
    private String login;
    private Set<String> roles;
    private String token;

    /**
     * Constructs a UserLoginResponse object.
     *
     * @param id     The ID of the user.
     * @param login  The email used for login.
     * @param roles  The set of roles assigned to the user.
     * @param token  The authentication token generated upon successful login.
     */
    public UserLoginResponse(Long id, String login, Set<String> roles, String token) {
        this.id = id;
        this.login = login;
        this.roles = roles;
        this.token = token;
    }

    /**
     * Converts a User entity object to a UserLoginResponse object.
     *
     * @param user  The User entity object.
     * @param token The authentication token generated upon successful login.
     * @return      A UserLoginResponse object created from the User entity.
     */
    public static UserLoginResponse fromEntity(User user, String token) {
        return new UserLoginResponse(user.getId(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()), token);
    }
}
