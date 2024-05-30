package be.labofitness.labo_fitness.bll.model.admin.anonymizeUser;
import be.labofitness.labo_fitness.domain.entity.User;

/**
 * Represents a response to an admin anonymizing a {@link User}.
 * This class encapsulates the {@link User}'s ID and a message indicating the outcome of the anonymization request.
 * <p>
 * {@code Example usage:}
 * </p>
 * <pre>{@code
 * User user = ...;
 * String message = "Anonymization ";
 * AdminAnonymizeUserResponse response = AdminAnonymizeUserResponse.fromEntity(user, message);
 * }</pre>
 *
 * @param id      The ID of the anonymized {@link User}.
 * @param message The message indicating the outcome of the anonymization request.
 * @see User
 */
public record AdminAnonymizeUserResponse(
        Long id,
        String message
) {

    /**
     * Creates an {@code AdminAnonymizeUserResponse} from a {@link User} entity and a message.
     * <p>
     * This method constructs the response object by extracting the user's ID and appending "successfully done"
     * to the provided message.
     * </p>
     *
     * @param user    The {@code User} entity.
     * @param message The message to append to "successfully done".
     * @return An {@code AdminAnonymizeUserResponse} containing the user's ID and the message.
     * @see User
     */
    public static AdminAnonymizeUserResponse fromEntity(User user, String message){
        return new AdminAnonymizeUserResponse(
                user.getId(),
                message + "successfully done"
        );
    }
}
