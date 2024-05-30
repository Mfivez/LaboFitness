package be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.addAccredition;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import be.labofitness.labo_fitness.il.utils.annotations.AccreditationValidor.AccreditationValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;


/**
 * Represents the request model for adding a new location for a professional.
 * <p>
 * This record encapsulates the accreditation type and the accreditation description of an Accredidation
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * AccreditationType accreditationType = "LICENCE";
 * String description = "ya i've got a big licence bro";
 * ProfessionalAddAccreditationRequest addRequest = new ProfessionalAddAccreditationRequest(
 *     accreditation, description
 * );
 * }</pre>
 *
 * @param accreditationType  The street of the new location. Must be a valid string with a minimum length of 3.
 * @param description  The number of the new location. Must be a valid string with a minimum length of 3.
 */
public record ProfessionalAddAccreditationRequest(

        @AccreditationValid
        AccreditationType accreditationType,

        @StringValid(entity = "accreditionRequest", field = "description", min = 4)
        String description

)

{

}
