package be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientGetPersonalTrainingSessionByClientIdRequest(
        @NotBlank(message = "Id cannot be blank")
        Long Id
) {
}
