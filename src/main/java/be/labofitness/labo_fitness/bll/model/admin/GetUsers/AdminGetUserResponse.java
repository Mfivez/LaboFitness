package be.labofitness.labo_fitness.bll.model.admin.GetUsers;
import be.labofitness.labo_fitness.domain.entity.User;

/**
 * Represents the response model for retrieving {@link User} information in the {@code admin module}.
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
     * @return An {@link AdminGetUserResponse} object created from the User entity.
     */
    public static AdminGetUserResponse fromEntity(User user){
        return new AdminGetUserResponse(
                user.getName(),
                user.getEmail(),
                user.getId(),
                user.isActive(),
                user.getRoles().toString()
        );
    }

}
