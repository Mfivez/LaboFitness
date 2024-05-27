package be.labofitness.labo_fitness.bll.model.request;

import jakarta.validation.constraints.NotBlank;

//TODO VALIDATORS

public record UserLoginRequest(
        @NotBlank(message = "email cannot be blank")
        String email,
        @NotBlank(message = "password cannot be blank")
        String password
) {


}
