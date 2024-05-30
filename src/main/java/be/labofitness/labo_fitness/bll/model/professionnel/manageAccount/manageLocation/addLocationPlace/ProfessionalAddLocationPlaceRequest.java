package be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.addLocationPlace;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;

/**
 * Represents the request model for adding a new location for a professional.
 * <p>
 * This record encapsulates the street, number, city, and zip code of the new location.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String street = "Main Street";
 * String number = "123";
 * String city = "Example City";
 * String zipCode = "12345";
 * ProfessionalAddLocationPlaceRequest addRequest = new ProfessionalAddLocationPlaceRequest(
 *     street, number, city, zipCode
 * );
 * }</pre>
 *
 * @param street  The street of the new location. Must be a valid string with a minimum length of 3.
 * @param number  The number of the new location. Must be a valid string with a minimum length of 3.
 * @param city    The city of the new location. Must be a valid string with a minimum length of 3.
 * @param zipCode The zip code of the new location. Must be a valid string with a minimum length of 3.
 */
public record ProfessionalAddLocationPlaceRequest(

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
