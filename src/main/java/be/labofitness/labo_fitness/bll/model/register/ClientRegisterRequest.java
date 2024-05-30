package be.labofitness.labo_fitness.bll.model.register;
import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.genderValidator.GenderValid;
import be.labofitness.labo_fitness.il.utils.annotations.monthValidator.MonthValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.PasswordValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 * Represents the request model for registering a new client.
 * <p>
 * This record encapsulates various details such as client's name, last name, date of birth, email,
 * password, gender, address, weight, and height.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String name = "John";
 * String lastName = "Doe";
 * Integer year = 1990;
 * String month = "January";
 * Integer day = 1;
 * String email = "john.doe@example.com";
 * String password = "password123";
 * Gender gender = Gender.MALE;
 * String street = "123 Main Street";
 * String number = "1A";
 * String city = "Cityville";
 * String zipCode = "12345";
 * Integer weight = 70;
 * Integer height = 180;
 *
 * ClientRegisterRequest request = new ClientRegisterRequest(name, lastName, year, month, day, email,
 *         password, new Address(gender, street, number, city, zipCode), weight, height);
 * }</pre>
 *
 * @param name        The name of the client.
 * @param lastName    The last name of the client.
 * @param year        The birth year of the client.
 * @param month       The birth month of the client.
 * @param day         The birth day of the client.
 * @param email       The email address of the client.
 * @param password    The password of the client.
 * @param gender      The gender of the client.
 * @param street      The street address of the client.
 * @param number      The house number of the client's address.
 * @param city        The city of residence of the client.
 * @param zipCode     The postal code of the client's address.
 * @param weight      The weight of the client (in kilograms).
 * @param height      The height of the client (in centimeters).
 */
public record ClientRegisterRequest (

        @NotNull(message = "error.client.name.null")
        @StringValid(entity = "client", field = "name", min = 3)
        String name,

        @NotNull(message = "error.client.lastName.null")
        @StringValid(entity = "client", field = "lastname", min = 3)
        String lastName,

        @NotNull(message = "error.client.year.null")
        @Range(min = 1940, max = 2010, message = "height must be between 1940 and 2010")
        Integer year,

        @NotNull(message = "error.client.month.null")
        @MonthValid
        String month,

        @NotNull(message = "error.client.day.null")
        @Range(min = 1, max = 31, message = "height must be between 1 and 31")
        Integer day,

        @NotNull(message = "error.client.email.null")
        @EmailValid
        String email,

        @NotNull(message = "error.client.password.null")
        @PasswordValid
        String password,

        @GenderValid
        Gender gender,

        @StringValid(entity = "professional", field = "street", min = 1)
        String street,

        @StringValid(entity = "professional", field = "number", min = 3)
        String number,

        @StringValid(entity = "professional", field = "city", min = 3)
        String city,

        @StringValid(entity = "professional", field = "zipcode", min = 3)
        String zipCode,

        @NotNull(message = "error.client.weight.null")
        @Range(min = 25, max = 500, message = "weight must be between 25 and 500")
        Integer weight,

        @NotNull(message = "error.client.height.null")
        @Range(min = 120, max = 350, message = "height must be between 120 and 350")
        Integer height

) {
}
