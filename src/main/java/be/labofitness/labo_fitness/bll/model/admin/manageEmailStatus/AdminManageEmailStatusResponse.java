package be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus;
import be.labofitness.labo_fitness.domain.entity.User;

/**
 * Represents the response model for managing the email status of a user in the admin module.
 * <p>
 * This record encapsulates the ID of the user and a message indicating the success of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * User user = userService.getOne(id);
 * String successMessage = "Email status update ";
 * AdminManageEmailStatusResponse response = AdminManageEmailStatusResponse.fromEntity( user , successMessage);
 * }</pre>
 *
 * @param id      The ID of the user.
 * @param message A message indicating the success of the operation.
 */
public record AdminManageEmailStatusResponse(

        Long id,
        String message

) {

    /**
     * Converts a {@link User} entity object to an {@link AdminManageEmailStatusResponse} object.
     *
     * @param user    The {@link User} entity object.
     * @param message The message indicating the success of the operation.
     * @return An {@link AdminManageEmailStatusResponse} object created from the User entity.
     */
    public static AdminManageEmailStatusResponse fromEntity(User user, String message){
        return new AdminManageEmailStatusResponse(
                user.getId(),
                message + "successfully done"
        );
    }
}
