package be.labofitness.labo_fitness.bll.model.request.user.getPhysiotherapist;

import jakarta.validation.constraints.NotBlank;

public record GetPhysioByNameRequest(
        @NotBlank(message = "name cannot be blank")
        String name
)
{}
