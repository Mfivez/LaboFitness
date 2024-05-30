package be.labofitness.labo_fitness.bll.model.admin.GetUsers;
import be.labofitness.labo_fitness.domain.entity.User;

/**
 * Represents the response model for retrieving {@link User} information in the admin module.
 * <p>
 * This record encapsulates various details about a user to be returned in response to a user retrieval request.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * User user = new User();
 * user.setName("John Doe");
 * user.setEmail("john.doe@example.com");
 * user.setId(123L);
 * user.setActive(true);
 * user.setRoles(Set.of(new Role("ADMIN")));
 * AdminGetUserResponse response = AdminGetUserResponse.fromEntity(user);
 * }</pre>
 *
 * @param name    The name of the user.
 * @param email   The email of the user.
 * @param id      The ID of the user.
 * @param isActive The active status of the user.
 * @param role    The role(s) of the user as a string.
 * @see User
 */
public record AdminGetUserResponse(
        String name,
        String email,
        Long id,
        Boolean isActive,
        String role
) {
    /**
     * Converts a {@link User} entity object to an {@link AdminGetUserResponse} object.
     *
     * @param user The {@link User} entity object.
     * @return An {@link AdminGetUserResponse} object created from the {@link User} entity.
     */
    public static AdminGetUserResponse fromEntity(User user) {
        return new AdminGetUserResponse(
                user.getName(),
                user.getEmail(),
                user.getId(),
                user.isActive(),
                user.getRoles().toString()
        );
    }
}
