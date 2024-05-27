package be.labofitness.labo_fitness.bll.model.request.user.getTrainingSession;

import jakarta.validation.constraints.NotBlank;

public record GetTrainingSessionsByCoachNameRequest(
        @NotBlank(message = "coach_name cannot be blank")
        String coach_name
) {
}
