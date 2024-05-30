package be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.updateAccreditation;
import be.labofitness.labo_fitness.domain.entity.Accreditation;


/**
 * Represents the response model for adding a new accredication to a professional.
 * <p>
 * This record encapsulates a message that indicates the result of the add operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String responseMessage = "accredidation added successfully.";
 * ProfessionalAddLocationPlaceResponse response = new ProfessionalAddLocationPlaceResponse(responseMessage);
 * }</pre>
 * @param id The id of the newly created accreditation
 * @param message The message indicating the result of the add operation.
 */
public record ProfessionalUpdateAccreditationResponse(

        String message,
        Long id
) {
    public static ProfessionalUpdateAccreditationResponse fromEntity(String message, Accreditation accreditation){
        return new ProfessionalUpdateAccreditationResponse(
                message + "successfully done",
                accreditation.getId()
        );
    }
}
