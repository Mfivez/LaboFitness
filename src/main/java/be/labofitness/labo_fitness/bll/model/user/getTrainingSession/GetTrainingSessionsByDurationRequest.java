package be.labofitness.labo_fitness.bll.model.user.getTrainingSession;

import jakarta.validation.constraints.Min;

public record GetTrainingSessionsByDurationRequest(
        @Min(value = 1, message = "Duration must be at least 1")
        int duration
) {
}
