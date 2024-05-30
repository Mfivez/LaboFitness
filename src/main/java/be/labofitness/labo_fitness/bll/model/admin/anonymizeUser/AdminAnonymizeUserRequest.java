package be.labofitness.labo_fitness.bll.model.admin.anonymizeUser;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a request to anonymize an admin user.
 * This class uses validation annotations to ensure data integrity before processing the request.
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * AdminAnonymizeUserRequest request = new AdminAnonymizeUserRequest("example@example.com");
 * }</pre>
 *
 * @param email The email of the user to be anonymized.
 *              Must not be {@code null} and must comply with the constraints defined by {@link EmailValid}.
 * @see EmailValid
 */
public record AdminAnonymizeUserRequest(

        @NotNull(message = "error.admin.user.email.blank")
        @EmailValid
        String email

) {
}
