package be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus;
import be.labofitness.labo_fitness.domain.entity.User;

/**
 * Represents the response model for managing the account status of a user in the admin module.
 * <p>
 * This record encapsulates the user ID and a message indicating the status of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * User user = new User();
 * String message = "Account activation ";
 * AdminManageAccountStatusResponse response = AdminManageAccountStatusResponse.fromEntity(user, message);
 * }</pre>
 *
 * @param id      The ID of the user whose account status was managed.
 * @param message A message indicating the status of the operation.
 * @see User
 */
public record AdminManageAccountStatusResponse(

        Long id,
        String message
) {

    /**
     * Converts a {@link User} entity object and a message into an {@link AdminManageAccountStatusResponse} object.
     *
     * @param user    The {@link User} entity object.
     * @param message The message indicating the status of the operation.
     * @return An {@link AdminManageAccountStatusResponse} object created from the {@link User} entity and the message.
     */
    public static AdminManageAccountStatusResponse fromEntity(User user, String message){
        return new AdminManageAccountStatusResponse(
                user.getId(),
                message + "successfully done"
        );
    }

}
