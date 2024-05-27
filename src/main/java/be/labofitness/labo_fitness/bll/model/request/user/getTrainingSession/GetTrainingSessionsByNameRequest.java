package be.labofitness.labo_fitness.bll.model.request.user.getTrainingSession;

import jakarta.validation.constraints.NotBlank;

public record GetTrainingSessionsByNameRequest(
        @NotBlank(message = "name cannot be blank")
        String name
) {
}
