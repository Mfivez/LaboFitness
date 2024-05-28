package be.labofitness.labo_fitness.bll.model.request.user.getTrainingSession;

import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;

public record GetTrainingSessionByRecommendedLvlRequest(
        /*@Pattern(regexp = "^(AMATEUR|DEBUTANT|CONFIRME|PROFESSIONNEL|GIGACHAD)$",
                message = "specialization must be " +
                        "'DEBUTANT' " +
                        "or 'AMATEUR'" +
                        "or 'CONFIRME'" +
                        "or 'PROFESSIONNEL'" +
                        "or 'GIGACHAD'")*/
        RecommendedLevel recommendedLevel
) {
}
