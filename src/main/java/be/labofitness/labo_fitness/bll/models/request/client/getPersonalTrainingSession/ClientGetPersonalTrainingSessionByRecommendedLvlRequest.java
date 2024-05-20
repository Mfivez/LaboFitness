package be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession;

import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import jakarta.validation.constraints.Pattern;

public record ClientGetPersonalTrainingSessionByRecommendedLvlRequest(
        Long Id,
        @Pattern(regexp = "^(AMATEUR|DEBUTANT|CONFIRME|PROFESSIONNEL|GIGACHAD)$",
                message = "specialization must be " +
                                                "'DEBUTANT' " +
                                                "or 'AMATEUR'" +
                                                "or 'CONFIRME'" +
                                                "or 'PROFESSIONNEL'" +
                                                "or 'GIGACHAD'")
        RecommendedLevel recommendedLevel
) {
}
