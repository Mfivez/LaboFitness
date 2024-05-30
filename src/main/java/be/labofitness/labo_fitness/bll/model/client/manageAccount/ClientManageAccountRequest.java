package be.labofitness.labo_fitness.bll.model.client.manageAccount;
import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.genderValidator.GenderValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

/**
 * Represents the request model for managing a client's account.
 * <p>
 * This record encapsulates various attributes of the client's account for management purposes.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String name = "John";
 * String lastName = "Doe";
 * String email = "example@example.com";
 * Gender gender = Gender.MALE;
 * String street = "123 Main Street";
 * String number = "5";
 * String city = "City";
 * String zipCode = "12345";
 * int weight = 75;
 * int height = 180;
 * ClientManageAccountRequest request = new ClientManageAccountRequest(name, lastName, email, gender, new Address(street, number, city, zipCode), weight, height);
 * }</pre>
 *
 * @param name     The name of the client.
 * @param lastName The last name of the client.
 * @param email    The email of the client.
 * @param gender   The gender of the client.
 * @param street   The street address of the client.
 * @param number   The street number of the client.
 * @param city     The city of the client.
 * @param zipCode  The zip code of the client.
 * @param weight   The weight of the client.
 * @param height   The height of the client.
 */
public record ClientManageAccountRequest (

        @NotNull(message = "error.client.name.null")
        @StringValid(entity = "client", field = "name", min = 3)
        String name,

        @NotNull(message = "error.client.lastName.null")
        @StringValid(entity = "client", field = "last name", min = 3)
        String lastName,

        @NotNull(message = "error.client.email.null")
        @EmailValid
        String email,

        @NotNull(message = "error.client.gender.null")
        @GenderValid
        Gender gender,

        @NotNull(message = "error.client.street.null")
        @StringValid(entity = "client", field = "street", min = 4)
        String street,

        @NotNull(message = "error.client.street.number.null")
        @StringValid(entity = "client", field = "number", min = 1)
        String number,

        @NotNull(message = "error.client.city.null")
        @StringValid(entity = "client", field = "city", min = 3)
        String city,

        @NotNull(message = "error.client.zipcode.null")
        @StringValid(entity = "client", field = "zip code", min = 3)
        String zipCode,


        @NotNull(message = "error.client.weight.null")
        @Range(min = 10, max = 500, message = "Client weight must be between 10 and 500")
        int weight,

        @NotNull(message = "error.client.height.null")
        @Range(min = 120, max = 350, message = "Client height must be between 120 and 350")
        int height
){
}
