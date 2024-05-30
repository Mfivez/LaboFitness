package be.labofitness.labo_fitness.bll.model.user.getTrainingSession;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for retrieving training sessions by coach name.
 * <p>
 * This record encapsulates the name of the coach whose training sessions are to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String coachName = "John Doe";
 *
 * GetTrainingSessionsByCoachNameRequest request = new GetTrainingSessionsByCoachNameRequest(coachName);
 * }</pre>
 *
 * @param coach_name The name of the coach whose training sessions are to be retrieved.
 */
public record GetTrainingSessionsByCoachNameRequest(

        @NotNull(message = "error.trainingSession.coachName.null")
        @StringValid(entity = "trainingSession", field = "coachName", min = 3)
        String coach_name
) {
}
