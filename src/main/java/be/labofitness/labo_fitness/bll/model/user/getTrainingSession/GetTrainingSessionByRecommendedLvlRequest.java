package be.labofitness.labo_fitness.bll.model.user.getTrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import be.labofitness.labo_fitness.il.utils.annotations.RecommendedLvlValidor.RecommendedLevelValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for retrieving training sessions by recommended level.
 * <p>
 * This record encapsulates the recommended level of the training sessions to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * RecommendedLevel recommendedLevel = RecommendedLevel.BEGINNER;
 *
 * GetTrainingSessionByRecommendedLvlRequest request = new GetTrainingSessionByRecommendedLvlRequest(recommendedLevel);
 * }</pre>
 *
 * @param recommendedLevel The recommended level of the training sessions to be retrieved.
 */
public record GetTrainingSessionByRecommendedLvlRequest(

        @NotNull(message = "error.trainingSession.recommendedLevel.null")
        @RecommendedLevelValid
        RecommendedLevel recommendedLevel

) {
}
