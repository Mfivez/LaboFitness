package be.labofitness.labo_fitness.bll.model.request;

import jakarta.validation.constraints.NotBlank;

//TODO VALIDATORS

public record UserLoginRequest(
        @NotBlank(message = "error.register.email.blank")
        String email,
        @NotBlank(message = "error.register.password.blank")
        String password
) {


}
