package be.labofitness.labo_fitness.bll.model.client.manageAccount.changePassword;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.PasswordValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for changing the password of a client's account.
 * <p>
 * This record encapsulates the old password and the new password.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String oldPassword = "old_password";
 * String newPassword = "new_password";
 * ClientChangePasswordRequest request = new ClientChangePasswordRequest(oldPassword, newPassword);
 * }</pre>
 *
 * @param oldPassword The old password of the client's account.
 * @param newPassword The new password of the client's account.
 */
public record ClientChangePasswordRequest (

        @NotNull(message = "error.client.oldPassword.null")
        @NotBlank(message = "error.client.oldPassword.blank")
        String oldPassword,

        @NotNull(message = "error.client.newPassword.null")
        @PasswordValid
        String newPassword

){
}
