package be.labofitness.labo_fitness.bll.models.request;

import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.User;
import jakarta.validation.constraints.NotBlank;

//TODO VALIDATORS
//BUG GITHUB
public record UserLoginRequest(
        @NotBlank(message = "email cannot be blank")
        String email,
        @NotBlank(message = "password cannot be blank")
        String password
) {


}
