package be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession;

import jakarta.validation.constraints.NotBlank;

public record ClientGetPersonalTrainingSessionsByCoachNameRequest(
        Long Id,
        @NotBlank(message = "coach_name cannot be blank")
        String coach_name
) {
}
