package be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClientGetPersonalTrainingSessionsByDurationRequest(
        Long Id,
        @Min(value = 1, message = "Duration must be at least 1")
        int duration
) {
}
