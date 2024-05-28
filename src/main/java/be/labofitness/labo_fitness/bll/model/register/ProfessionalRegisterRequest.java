package be.labofitness.labo_fitness.bll.model.register;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.CustomEmailValidator;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.CustomPasswordValidator;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.ValidatorMessageCustom;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.Month;

public record ProfessionalRegisterRequest(


        @NotBlank(message = "error.professional.name.blank")
        @ValidatorMessageCustom(entity = "client", field = "name", min = 3)
        String name,

        @NotBlank(message = "error.professional.lastname.blank")
        @ValidatorMessageCustom(entity = "client", field = "name", min = 3)
        String lastName,

        @NotNull(message = "error.professional.year.null")
        @Min(value = 1940, message = "Year of birth must be no earlier than 1940")
        @Max(value = 2009, message = "Year of birth must be no later than 2009")
        int year,

        @NotNull(message = "error.professional.month.null")
        @Min(value = 1, message = "Month must be between 1 and 12")
        @Max(value = 12, message = "Month must be between 1 and 12")
        Month month,

        @Min(value = 1, message = "Day must be at least 1")
        @Max(value = 31, message = "Day must be at most 31")
        @NotNull(message = "error.professional.day.null")
        int day,

        @NotBlank(message = "error.professional.mail.blank")
        @CustomEmailValidator
        String email,

        @NotBlank(message = "error.professional.password.blank")
        @CustomPasswordValidator
        String password,

        @NotNull(message = "error.professional.accreditation.null")
        AccreditationType accreditation,

        @NotBlank(message = "error.professional.accreditation.description.blank")
        @ValidatorMessageCustom(entity = "client", field = "name", min = 3)
        String accreditationDescription,

        @NotNull(message = "error.professional.role.null")
        String role


) {
}
