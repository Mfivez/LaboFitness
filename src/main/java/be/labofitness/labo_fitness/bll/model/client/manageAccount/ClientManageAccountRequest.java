package be.labofitness.labo_fitness.bll.model.client.manageAccount;

import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.CustomEmailValidator;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.ValidatorMessageCustom;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.ValidatorUtils;
import jakarta.validation.constraints.*;
import static be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.ValidatorUtils.*;

public record ClientManageAccountRequest (

        @NotBlank(message = "error.client.name.blank")
        @ValidatorMessageCustom(entity = "client", field = "name", min = 3)
        String name,


        @NotBlank(message = "error.client.lastName.blank")
        @ValidatorMessageCustom(entity = "client", field = "last name", min = 3)
        String lastName,


        @NotBlank(message = "error.client.email.blank")
        @CustomEmailValidator
        String email,


        Gender gender,


        @NotBlank(message = "error.client.street.blank")
        @ValidatorMessageCustom(entity = "client", field = "street", min = 4)
        String street,


        @NotBlank(message = "error.client.street.number.blank")
        @ValidatorMessageCustom(entity = "client", field = "number", min = 1)
        String number,


        @NotBlank(message = "error.client.city.blank")
        @ValidatorMessageCustom(entity = "client", field = "city", min = 3)
        String city,


        @NotBlank(message = "error.client.zipcode.blank")
        @ValidatorMessageCustom(entity = "client", field = "zip code", min = 3)
        String zipCode,


        @NotNull(message = "error.client.weight.null")
        @Min(value = 10, message = "Client weight must be at least 10 kg")
        @Max(value = 500, message = "Client weight can't be over 500 kg")
        int weight,

        @NotNull(message = "error.client.height.null")
        @Min(value = 120, message = "Client height must be at least 120 cm")
        @Max(value = 350, message = "Client height can't be over 350 cm")
        int height
){
}
