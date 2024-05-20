package be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClientGetPersonalTrainingSessionsByNameRequest(
        Long Id,
        @NotBlank(message = "name cannot be blank")
        String name
) {
}
