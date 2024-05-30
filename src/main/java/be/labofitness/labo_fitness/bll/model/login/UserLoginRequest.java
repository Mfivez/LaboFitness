package be.labofitness.labo_fitness.bll.model.login;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.PasswordValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for user login.
 * <p>
 * This record encapsulates the email and password of the user for authentication.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String userEmail = "example@example.com";
 * String userPassword = "examplePassword";
 * UserLoginRequest loginRequest = new UserLoginRequest(userEmail, userPassword);
 * }</pre>
 *
 * @param email    The email of the user for authentication.
 * @param password The password of the user for authentication.
 */
public record UserLoginRequest(

        @NotNull(message = "error.login.email.blank")
        @EmailValid
        String email,

        @NotNull(message = "error.login.password.blank")
        @PasswordValid
        String password

) {
}
