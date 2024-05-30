package be.labofitness.labo_fitness.bll.model.coach.manageAccount.changePassword;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.PasswordValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for changing the password of a coach's account.
 * <p>
 * This record encapsulates the old password and the new password.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String oldPassword = "oldPassword123";
 * String newPassword = "newPassword456";
 * CoachChangePasswordRequest request = new CoachChangePasswordRequest(oldPassword, newPassword);
 * }</pre>
 *
 * @param oldPassword The old password of the coach's account.
 * @param newPassword The new password for the coach's account.
 */
public record CoachChangePasswordRequest (

        @NotNull(message = "error.coach.oldPassword.null")
        @NotBlank(message = "error.coach.oldPassword.blank")
        String oldPassword,

        @NotNull(message = "error.coach.newPassword.null")
        @PasswordValid
        String newPassword

) {
}
