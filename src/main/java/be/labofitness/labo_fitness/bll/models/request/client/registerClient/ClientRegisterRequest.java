package be.labofitness.labo_fitness.bll.models.request.client.registerClient;

import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.CustomEmailValidator;
import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.CustomPasswordValidator;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.ValidatorMessageCustom;
import jakarta.validation.constraints.*;

import java.time.Month;



public record ClientRegisterRequest (

        @NotBlank
        @ValidatorMessageCustom(entity = "client", field = "name", min = 3)
        String name,

        @NotBlank
        @ValidatorMessageCustom(entity = "client", field = "lastname", min = 3)
        String lastName,


        @NotNull(message = "error.client.year.blank")
        @Min(value = 1940, message = "Year of birth must be no earlier than 1940")
        @Max(value = 2009, message = "Year of birth must be no later than 2009")
        int year,


        @NotNull(message = "error.client.month.null")
        @Min(value = 1, message = "Month must be between 1 and 12")
        @Max(value = 12, message = "Month must be between 1 and 12")
        Month month,


        @Min(value = 1, message = "Day must be at least 1")
        @Max(value = 31, message = "Day must be at most 31")
        @NotNull(message = "error.client.day.null")
        int day,


        @NotBlank(message = "error.client.mail.blank")
        @CustomEmailValidator
        String email,


        @NotBlank(message = "error.client.password.blank")
        @CustomPasswordValidator
        String password,


        Gender gender,


        @NotBlank(message = "error.client.street.blank")
        @ValidatorMessageCustom(entity = "professional", field = "street", min = 3)
        String street,


        @NotBlank(message = "error.client.street.number.blank")
        @ValidatorMessageCustom(entity = "professional", field = "number", min = 3)
        String number,


        @NotBlank(message = "error.client.city.blank")
        @ValidatorMessageCustom(entity = "professional", field = "city", min = 3)
        String city,


        @NotBlank(message = "error.client.zipcode.blank")
        @ValidatorMessageCustom(entity = "professional", field = "zipcode", min = 3)
        String zipCode,


        @NotNull(message = "error.client.weight.null")
        @Min(value = 10, message = "Client weight must be at least 10 kg")
        @Max(value = 500, message = "Client weight can't be over 500 kg")
        int weight,

        @NotNull(message = "error.client.height.null")
        @Min(value = 120, message = "Client height must be at least 120 cm")
        @Max(value = 350, message = "Client height can't be over 350 cm")
        int height
) {



}
