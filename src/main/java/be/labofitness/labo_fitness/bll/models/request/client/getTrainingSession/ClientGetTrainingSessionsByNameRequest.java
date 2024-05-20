package be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession;

import jakarta.validation.constraints.NotBlank;

public record ClientGetTrainingSessionsByNameRequest(
        @NotBlank(message = "name cannot be blank")
        String name
) {
}
