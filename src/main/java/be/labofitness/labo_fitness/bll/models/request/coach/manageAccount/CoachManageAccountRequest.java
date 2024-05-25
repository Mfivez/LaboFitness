package be.labofitness.labo_fitness.bll.models.request.coach.manageAccount;

import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.ValidatorMessageCustom;
import jakarta.validation.constraints.*;

public record CoachManageAccountRequest(

        @NotBlank(message = "error.coach.name.blank")
        @ValidatorMessageCustom(entity = "coach", field = "name", min = 3)
        String name,


        @NotBlank(message = "error.coach.lastName.blank" )
        @ValidatorMessageCustom(entity = "coach", field = "lastName", min = 3)
        String lastName,


        @NotBlank(message = "error.coach.email.blank")
        @Email
        String email,


        Gender gender,


        @NotBlank(message = "error.coach.street.blank")
        @ValidatorMessageCustom(entity = "coach", field = "street", min = 4)
        String street,


        @NotBlank(message = "error.coach.number.blank")
        @ValidatorMessageCustom(entity = "coach", field = "number", min = 1)
        String number,

        @NotBlank(message = "error.coach.city.blank")
        @ValidatorMessageCustom(entity = "coach", field = "city", min = 3)
        String city,

        @NotBlank(message = "error.coach.zipcode.blank")
        @ValidatorMessageCustom(entity = "coach", field = "zipcode", min = 3)
        String zipCode,

        @NotNull(message = "error.coach.pricePerHour.null")
        @Min(value = 5)
        int pricePerHour,

        @NotNull(message = "error.coach.remote.null")
        boolean isRemote
) {

}
