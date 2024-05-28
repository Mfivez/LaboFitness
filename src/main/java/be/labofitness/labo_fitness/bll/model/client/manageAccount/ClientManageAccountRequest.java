package be.labofitness.labo_fitness.bll.model.client.manageAccount;

import be.labofitness.labo_fitness.domain.enums.Gender;
import jakarta.validation.constraints.*;

public record ClientManageAccountRequest (

        @NotBlank(message = "Client name cannot be a white space")
        @NotNull(message ="Client name cannot be null")
        @Size(min= 3, message = "Client name must be at least 3 characters long")
        String name,


        @NotBlank(message = "Client last name cannot be a white space" )
        @NotNull(message = "Client last name cannot be null")
        @Size(min = 3, message = "Client last name must be at least 3 characters long")
        String lastName,


        @NotBlank(message = "Client email cannot be a white space")
        @NotNull(message = "Client email cannot be null")
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


        @NotNull(message = "Client weight can't be null")
        @Min(value = 10, message = "Client weight must be at least 10 kg")
        @Max(value = 500, message = "Client weight can't be over 500 kg")
        int weight,

        @NotNull(message = "Client height can't be null")
        @Min(value = 120, message = "Client height must be at least 120 cm")
        @Max(value = 350, message = "Client height can't be over 350 cm")
        int height
){
}
