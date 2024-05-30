package be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.updateAccreditation;

import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for adding a new location for a professional.
 * <p>
 * This record encapsulates the accreditation type and the accreditation description of an Accredidation
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * accreditationId = "1";
 * String description = "ya i've got a big BIG licence bro";
 * UpdateAccreditationRequest addRequest = new UpdateAccreditationRequest(
 *     accreditationId, description
 * );
 * }</pre>
 *
 * @param accreditationId  the id of an already existing accreditation with a minimum value of 1
 * @param description  the description of the accreditation. Must be a valid string with a minimum length of 4.
 */
public record ProfessionalUpdateAccreditationRequest(

        @NotNull(message = "error.professional.accreditationId.null")
        @NotBlank(message = "error.professional.accreditationId.blank")
        @Min(value = 1)
        Long accreditationId,

        @StringValid(entity = "accreditionRequest", field = "description", min = 4)
        String description
) {
}
