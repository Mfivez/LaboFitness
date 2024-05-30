package be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for managing the account status of a user in the admin module.
 * <p>
 * This record encapsulates the necessary information to update the account status of a user.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * AdminManageAccountStatusRequest request = new AdminManageAccountStatusRequest(
 *     "example@example.com",
 *     true
 * );
 * }</pre>
 *
 * @param email    The email of the user whose account status is being managed.
 * @param isActive The new active status of the user's account.
 */
public record AdminManageAccountStatusRequest(

        @NotBlank(message = "error.admin.user.email.blank")
        @EmailValid
        String email,

        @NotNull(message = "error.admin.user.isActive.null")
        @NotBlank(message = "error.admin.user.isActive.blank")
        Boolean isActive

){
}
