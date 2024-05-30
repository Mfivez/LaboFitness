package be.labofitness.labo_fitness.bll.model.admin.GetUsers;
import be.labofitness.labo_fitness.domain.entity.User;

import java.util.List;


/**
 * Represents the request model for retrieving {@link User} information in the admin module.
 * <p>
 * This record encapsulates various criteria that can be used to filter and retrieve users.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * List<String> roles = List.of("ADMIN", "USER");
 * String email = "example@example.com";
 * String name = "John Doe";
 * Long id = 123L;
 * Boolean isActive = true;
 * Boolean isRemote = false;
 * String inamiNumber = "123456";
 * AdminGetUserRequest request = new AdminGetUserRequest(roles, email, name, id, isActive, isRemote, inamiNumber);
 * }</pre>
 *
 * @param roles      The roles of the user(s) to be retrieved.
 * @param userEmail  The email of the user(s) to be retrieved.
 * @param name       The name of the user(s) to be retrieved.
 * @param id         The ID of the user(s) to be retrieved.
 * @param isActive   The active status of the user(s) to be retrieved.
 * @param isRemote   The remote status of the user(s) to be retrieved.
 * @param inamiNumber The INAMI number of the user(s) to be retrieved.
 * @see User
 */
public record AdminGetUserRequest(

        List<String> roles,

        String userEmail,

        String name,

        Long id,

        Boolean isActive,

        Boolean isRemote,

        String inamiNumber

) {
}
