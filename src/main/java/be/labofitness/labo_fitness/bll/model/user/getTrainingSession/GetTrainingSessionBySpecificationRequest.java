package be.labofitness.labo_fitness.bll.model.user.getTrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;

public record GetTrainingSessionBySpecificationRequest(

        Long clientId,

        RecommendedLevel recommendedLevel,

        String coachName,

        Integer duration,

        String trainingName,

        Boolean isInscriptionOpen


) {
}
