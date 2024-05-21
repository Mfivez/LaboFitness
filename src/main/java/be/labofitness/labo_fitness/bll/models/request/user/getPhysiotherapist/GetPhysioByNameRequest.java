package be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist;

import jakarta.validation.constraints.NotBlank;

public record GetPhysioByNameRequest(
        @NotBlank(message = "name cannot be blank")
        String name
)
{}
