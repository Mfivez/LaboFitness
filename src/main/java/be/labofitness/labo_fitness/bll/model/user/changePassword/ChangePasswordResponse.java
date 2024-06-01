package be.labofitness.labo_fitness.bll.model.user.changePassword;
import be.labofitness.labo_fitness.domain.entity.User;

/**
 * Represents the response model for changing the password of a client's account.
 * <p>
 * This record encapsulates the ID of the client and a message indicating the success of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * User user = userService.getById(id);
 * String successMessage = "Password ";
 * ChangePasswordResponse response = ChangePasswordResponse.fromEntity(user, successMessage);
 * }</pre>
 *
 * @param id      The ID of the client.
 * @param message A message indicating the success of the operation.
 */
public record ChangePasswordResponse(
        Long id,
        String message
) {

    /**
     * Converts a {@link User} entity object to a {@link ChangePasswordResponse} object.
     *
     * @param user  The {@link User} entity object.
     * @param message The message indicating the success of the operation.
     * @return A {@link ChangePasswordResponse} object created from the User entity.
     */
    public static ChangePasswordResponse fromEntity(User user, String message){
        return new ChangePasswordResponse(
                user.getId(),
                message + "successfully updated"
        );
    }
}
