package be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.changePassWord;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.PasswordValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for changing the password of a physiotherapist.
 * <p>
 * This record encapsulates the old password and the new password.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String oldPassword = "oldPassword123";
 * String newPassword = "newPassword456";
 * PhysiotherapistChangePasswordRequest request = new PhysiotherapistChangePasswordRequest(oldPassword, newPassword);
 * }</pre>
 *
 * @param oldPassword The old password of the physiotherapist.
 * @param newPassword The new password of the physiotherapist.
 */
public record PhysiotherapistChangePasswordRequest(

        @NotNull(message = "error.physiotherapist.oldPassword.null")
        @NotBlank(message = "error.physiotherapist.oldPassword.blank")
        String oldPassword,

        @NotNull(message = "error.physiotherapist.newPassword.null")
        @PasswordValid
        String newPassword

) {
}
