package be.labofitness.labo_fitness.bll.model.professionnel.manageLocation;

/**
 * Represents the response model for updating the location details of a professional.
 * <p>
 * This record encapsulates a message that indicates the result of the update operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String responseMessage = "Location updated successfully.";
 * ProfessionalUpdateLocationPlaceResponse response = new ProfessionalUpdateLocationPlaceResponse(responseMessage);
 * }</pre>
 *
 * @param message The message indicating the result of the update operation.
 */
public record ProfessionalUpdateLocationPlaceResponse(
        String message
) { }
