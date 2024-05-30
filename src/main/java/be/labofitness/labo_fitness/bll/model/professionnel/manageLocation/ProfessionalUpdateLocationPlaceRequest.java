package be.labofitness.labo_fitness.bll.model.professionnel.manageLocation;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for updating the location details of a professional.
 * <p>
 * This record encapsulates the locationId, street, number, city, and zipCode.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Long locationId = 1L;
 * String street = "Main Street";
 * String number = "123";
 * String city = "Example City";
 * String zipCode = "12345";
 * ProfessionalUpdateLocationPlaceRequest updateRequest = new ProfessionalUpdateLocationPlaceRequest(
 *     locationId, street, number, city, zipCode
 * );
 * }</pre>
 *
 * @param locationId The ID of the location. Must not be null or blank and must be at least 1.
 * @param street     The street of the location. Must be a valid string with a minimum length of 3.
 * @param number     The number of the location. Must be a valid string with a minimum length of 3.
 * @param city       The city of the location. Must be a valid string with a minimum length of 3.
 * @param zipCode    The zip code of the location. Must be a valid string with a minimum length of 3.
 */
public record ProfessionalUpdateLocationPlaceRequest(

        @NotNull(message = "error.professional.locationid.null")
        @NotBlank(message = "error.professional.locationid.blank")
        @Min(value = 1)
        Long locationId,

        @StringValid(entity = "professional", field = "street", min = 3)
        String street,

        @StringValid(entity = "professional", field = "number", min = 3)
        String number,

        @StringValid(entity = "professional", field = "city", min = 3)
        String city,

        @StringValid(entity = "professional", field = "zipcode", min = 3)
        String zipCode
) {
}
