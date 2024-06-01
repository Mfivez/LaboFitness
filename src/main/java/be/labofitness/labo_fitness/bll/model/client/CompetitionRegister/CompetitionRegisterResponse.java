package be.labofitness.labo_fitness.bll.model.client.CompetitionRegister;

/**
 * Represents the response model for registering for a competition by a client.
 * <p>
 * This record encapsulates a confirmation message.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String confirmationMessage = "Registration successful!";
 * CompetitionRegisterResponse response = new CompetitionRegisterResponse(confirmationMessage);
 * }</pre>
 *
 * @param ConfirmationMessage The confirmation message.
 */
public record CompetitionRegisterResponse(
    String ConfirmationMessage
) {

    public static CompetitionRegisterResponse fromEntity(String ConfirmationMessage){
        return new CompetitionRegisterResponse(
                ConfirmationMessage
        );
    }
}
