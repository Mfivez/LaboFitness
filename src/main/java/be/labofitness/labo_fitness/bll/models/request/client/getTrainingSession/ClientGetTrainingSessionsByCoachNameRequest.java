package be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession;

import jakarta.validation.constraints.NotBlank;

public record ClientGetTrainingSessionsByCoachNameRequest(
        @NotBlank(message = "coach_name cannot be blank")
        String coach_name
) {
}
