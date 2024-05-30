package be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription;

/**
 * Represents the response model for managing event inscription in the coach module.
 * <p>
 * This record encapsulates a message indicating the success of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String successMessage = "Event inscription managed successfully.";
 * ManageEventInscriptionResponse response = new ManageEventInscriptionResponse(successMessage);
 * }</pre>
 *
 * @param message A message indicating the success of the operation.
 */
public record ManageEventInscriptionResponse(
    String message
) {
}
