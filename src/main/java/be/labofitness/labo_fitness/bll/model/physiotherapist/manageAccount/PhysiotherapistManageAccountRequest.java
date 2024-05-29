package be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount;

import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.CustomEmailValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PhysiotherapistManageAccountRequest(

        @NotBlank(message = "error.physiotherapist.name.blank")
        @Size(min= 3, message = "Coach name must be at least 3 characters long")
        String name,

        @NotBlank(message = "error.physiotherapist.lastName.blank" )
        @Size(min = 3, message = "Coach last name must be at least 3 characters long")
        String lastName,

        @NotBlank(message = "error.physiotherapist.email.blank")
        @CustomEmailValidator
        String email,

        Gender gender,

        @NotBlank(message = "error.physiotherapist.street.blank")
        @Size(min = 4, message = "Street name must be at least 4 characters long")
        String street,

        @NotBlank(message = "error.physiotherapist.number.blank")
        @Size(min = 1, message = "Street number must be at least 1 character")
        String number,

        @NotBlank(message = "error.physiotherapist.city.blank")
        @Size(min = 3, message = "City must be at least 3 characters")
        String city,

        @NotBlank(message = "error.physiotherapist.zipcode.blank")
        @Size(min = 3, message = "City zipcode must be at least 3 characters")
        String zipCode,

        @NotBlank(message = "error.physiotherapist.name.blank")
        @Size(min= 11, max = 11, message = "INAMI number must be eleven characters")
        String inamiNumber
) {
}
