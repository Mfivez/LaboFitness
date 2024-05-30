package be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount;
import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.genderValidator.GenderValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 * Represents the request model for managing the account of a physiotherapist.
 * <p>
 * This record encapsulates various details such as name, email, gender, address, and INAMI number.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String name = "John";
 * String lastName = "Doe";
 * String email = "john.doe@example.com";
 * Gender gender = Gender.MALE;
 * String street = "123 Main Street";
 * String number = "1A";
 * String city = "Cityville";
 * String zipCode = "12345";
 * int inamiNumber = 12345678901;
 *
 * PhysiotherapistManageAccountRequest request = new PhysiotherapistManageAccountRequest(name, lastName, email, new Address(gender, street, number, city, zipCode), inamiNumber);
 * }</pre>
 *
 * @param name       The name of the physiotherapist.
 * @param lastName   The last name of the physiotherapist.
 * @param email      The email address of the physiotherapist.
 * @param gender     The gender of the physiotherapist.
 * @param street     The street address of the physiotherapist.
 * @param number     The house number of the physiotherapist's address.
 * @param city       The city of residence of the physiotherapist.
 * @param zipCode    The postal code of the physiotherapist's address.
 * @param inamiNumber The INAMI number of the physiotherapist.
 */
public record PhysiotherapistManageAccountRequest(

        @NotNull(message = "error.physiotherapist.name.null")
        @StringValid(entity = "physiotherapist", field = "name", min = 3 )
        String name,

        @NotNull(message = "error.physiotherapist.lastName.null")
        @StringValid(entity = "physiotherapist", field = "lastName", min = 3 )
        String lastName,

        @NotNull(message = "error.physiotherapist.email.null")
        @EmailValid
        String email,

        @GenderValid
        Gender gender,

        @StringValid(entity = "physiotherapist", field = "street", min = 4 )
        String street,

        @StringValid(entity = "physiotherapist", field = "number", min = 1 )
        String number,

        @StringValid(entity = "physiotherapist", field = "city", min = 3 )
        String city,

        @StringValid(entity = "physiotherapist", field = "zipCode", min = 3 )
        String zipCode,

        @NotNull(message = "error.physiotherapist.inamiNumber.null")
        @Range(min = 11, max = 11)
        int inamiNumber

) {
}
