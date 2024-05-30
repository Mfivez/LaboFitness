package be.labofitness.labo_fitness.bll.model.register;

import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import be.labofitness.labo_fitness.il.utils.annotations.AccreditationValidor.AccreditationValid;
import be.labofitness.labo_fitness.il.utils.annotations.ValidatorRole.RoleValid;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.monthValidator.MonthValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.PasswordValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import java.time.Month;

/**
 * Represents the request model for registering a new professional.
 * <p>
 * This record encapsulates various details such as professional's name, last name, date of birth, email,
 * password, accreditation, accreditation description, and role.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String name = "John";
 * String lastName = "Doe";
 * int year = 1980;
 * Month month = Month.JANUARY;
 * int day = 1;
 * String email = "john.doe@example.com";
 * String password = "password123";
 * AccreditationType accreditation = AccreditationType.CERTIFIED;
 * String accreditationDescription = "Certified fitness trainer";
 * String role = "Trainer";
 *
 * ProfessionalRegisterRequest request = new ProfessionalRegisterRequest(name, lastName, year, month, day, email,
 *         password, accreditation, accreditationDescription, role);
 * }</pre>
 *
 * @param name                    The name of the professional.
 * @param lastName                The last name of the professional.
 * @param year                    The birth year of the professional.
 * @param month                   The birth month of the professional.
 * @param day                     The birth day of the professional.
 * @param email                   The email address of the professional.
 * @param password                The password of the professional.
 * @param accreditation           The accreditation type of the professional.
 * @param accreditationDescription The description of the professional's accreditation.
 * @param role                    The role of the professional.
 */
public record ProfessionalRegisterRequest(

        @NotNull(message = "error.professional.name.null")
        @StringValid(entity = "client", field = "name", min = 3)
        String name,

        @NotNull(message = "error.professional.lastname.null")
        @StringValid(entity = "client", field = "name", min = 3)
        String lastName,

        @NotNull(message = "error.professional.year.null")
        @Range(min = 1940, max = 2010, message = "Year of birth must be between 1940 and 2010")
        int year,

        @NotNull(message = "error.professional.month.null")
        @MonthValid
        Month month,

        @NotNull(message = "error.professional.day.null")
        @Range(min = 1, max = 31, message = "day of birth must be between 1 and 31")
        int day,

        @NotNull(message = "error.professional.mail.null")
        @EmailValid
        String email,

        @NotNull(message = "error.professional.password.null")
        @PasswordValid
        String password,

        @NotNull(message = "error.professional.accreditation.null")
        @AccreditationValid
        AccreditationType accreditation,

        @NotNull(message = "error.professional.accreditation.description.null")
        @StringValid(entity = "client", field = "name", min = 3)
        String accreditationDescription,

        @NotNull(message = "error.professional.role.null")
        @RoleValid
        String role


) {
}
