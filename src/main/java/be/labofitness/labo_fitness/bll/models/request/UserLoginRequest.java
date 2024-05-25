package be.labofitness.labo_fitness.bll.models.request;

import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.User;
import jakarta.validation.constraints.NotBlank;

//TODO VALIDATORS

public record UserLoginRequest(
        @NotBlank(message = "error.register.email.blank")
        String email,
        @NotBlank(message = "error.register.password.blank")
        String password
) {


}
