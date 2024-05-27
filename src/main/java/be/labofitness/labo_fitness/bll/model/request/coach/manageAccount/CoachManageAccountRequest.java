package be.labofitness.labo_fitness.bll.model.request.coach.manageAccount;

import be.labofitness.labo_fitness.domain.enums.Gender;
import jakarta.validation.constraints.*;

public record CoachManageAccountRequest(

        @NotBlank(message = "Coach name cannot be a white space")
        @NotNull(message ="Coach name cannot be null")
        @Size(min= 3, message = "Coach name must be at least 3 characters long")
        String name,


        @NotBlank(message = "Coach last name cannot be a white space" )
        @NotNull(message = "Coach last name cannot be null")
        @Size(min = 3, message = "Coach last name must be at least 3 characters long")
        String lastName,


        @NotBlank(message = "Coach email cannot be a white space")
        @NotNull(message = "Coach email cannot be null")
        @Email
        String email,


        Gender gender,


        @NotBlank(message = "Street name cannot be a white space")
        @NotNull(message = "Street name cannot be null" )
        @Size(min = 4, message = "Street name must be at least 4 characters long")
        String street,


        @NotBlank(message = "Street number cannot be a white space")
        @NotNull(message = "Street number cannot be null")
        @Size(min = 1, message = "Street number must be at least 1 character")
        String number,

        @NotBlank(message = "City cannot be a white space")
        @NotNull(message = "City cannot be null")
        @Size(min = 3, message = "City must be at least 3 characters")
        String city,

        @NotBlank(message = "City zipcode cannot be a white space")
        @NotNull(message = "City zipcode cannot be null")
        @Size(min = 3, message = "City zipcode must be at least 3 characters")
        String zipCode,

        @NotNull(message = "Price per hour can't be null")
        @Min(value = 5)
        int pricePerHour,

        @NotNull(message = "Remote field must be filled")
        boolean isRemote
) {

}
