package be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents the request model for managing the email status of a user in the admin module.
 * <p>
 * This record encapsulates the email and the email active status of the user.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String email = "example@example.com";
 * Boolean emailActive = true;
 * AdminManageEmailStatusRequest request = new AdminManageEmailStatusRequest(email, emailActive);
 * }</pre>
 *
 * @param email       The email of the user whose email status is being managed.
 * @param emailActive The email active status of the user.
 */
public record AdminManageEmailStatusRequest(

        @EmailValid
        String email,

        @NotBlank(message = "error.admin.user.emailActive.null")
        @NotBlank(message = "error.admin.user.emailActive.blank")
        Boolean emailActive

) {
}
