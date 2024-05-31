package be.labofitness.labo_fitness.bll.model.coach.manageAccount;
import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.genderValidator.GenderValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.*;

/**
 * Represents the request model for managing the account of a coach.
 * <p>
 * This record encapsulates various details of a coach's account that can be managed.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String name = "John";
 * String lastName = "Doe";
 * String email = "john@example.com";
 * Gender gender = Gender.MALE;
 * String street = "123 Main Street";
 * String number = "5A";
 * String city = "City";
 * String zipCode = "12345";
 * int pricePerHour = 20;
 * boolean isRemote = true;
 * CoachManageAccountRequest request = new CoachManageAccountRequest(name, lastName, email, gender, new Address(street, number, city, zipCode), pricePerHour, isRemote);
 * }</pre>
 *
 * @param name         The name of the coach.
 * @param lastName     The last name of the coach.
 * @param email        The email of the coach.
 * @param gender       The gender of the coach.
 * @param street       The street address of the coach.
 * @param number       The house number of the coach.
 * @param city         The city of the coach.
 * @param zipCode      The ZIP code of the coach.
 * @param pricePerHour The price per hour charged by the coach.
 * @param isRemote     A boolean indicating whether the coach offers remote services.
 */
public record CoachManageAccountRequest(

        @NotNull(message = "error.coach.name.null")
        @StringValid(entity = "coach", field = "name", min = 3)
        String name,

        @NotNull(message = "error.coach.lastName.null" )
        @StringValid(entity = "coach", field = "lastName", min = 3)
        String lastName,

        @NotNull(message = "error.coach.email.null")
        @EmailValid
        String email,

        @GenderValid
        Gender gender,

        @StringValid(entity = "coach", field = "street", min = 4)
        String street,

        @StringValid(entity = "coach", field = "number", min = 1)
        String number,

        @StringValid(entity = "coach", field = "city", min = 3)
        String city,

        @StringValid(entity = "coach", field = "zipcode", min = 3)
        String zipCode,

        @NotNull(message = "error.coach.pricePerHour.null")
        @Min(value = 5)
        int pricePerHour,

        @NotNull(message = "error.coach.remote.null")
        boolean isRemote
) {

}
