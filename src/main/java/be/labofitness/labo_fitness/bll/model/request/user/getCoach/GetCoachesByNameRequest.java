package be.labofitness.labo_fitness.bll.model.request.user.getCoach;

import jakarta.validation.constraints.NotBlank;

public record GetCoachesByNameRequest(
        @NotBlank(message = "name cannot be blank")
        String name
)
{}
