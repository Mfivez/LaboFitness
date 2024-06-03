package be.labofitness.labo_fitness.bll.model.user.changePassword;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.PasswordValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for changing the password of a user's account.
 * <p>
 * This record encapsulates the old password and the new password.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String oldPassword = "old_password";
 * String newPassword = "new_password";
 * ChangePasswordRequest request = new ChangePasswordRequest(oldPassword, newPassword);
 * }</pre>
 *
 * @param oldPassword The old password of the user's account.
 * @param newPassword The new password of the user's account.
 */
public record ChangePasswordRequest(

        @NotNull(message = "error.user.oldPassword.null")
        @NotBlank(message = "error.user.oldPassword.blank")
        String oldPassword,

        @NotNull(message = "error.user.newPassword.null")
        @PasswordValid
        String newPassword

){
}
