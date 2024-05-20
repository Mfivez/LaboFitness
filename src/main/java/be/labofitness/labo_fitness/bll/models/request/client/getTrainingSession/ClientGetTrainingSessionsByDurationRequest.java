package be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession;

import jakarta.validation.constraints.Min;

public record ClientGetTrainingSessionsByDurationRequest(
        @Min(value = 1, message = "Duration must be at least 1")
        int duration
) {
}
