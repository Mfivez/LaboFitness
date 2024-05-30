package be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.addLocationPlace;

/**
 * Represents the response model for adding a new location for a professional.
 * <p>
 * This record encapsulates a message that indicates the result of the add operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String responseMessage = "Location added successfully.";
 * ProfessionalAddLocationPlaceResponse response = new ProfessionalAddLocationPlaceResponse(responseMessage);
 * }</pre>
 *
 * @param message The message indicating the result of the add operation.
 */
public record ProfessionalAddLocationPlaceResponse(
        String message
) {

}
