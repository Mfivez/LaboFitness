package be.labofitness.labo_fitness.bll.model.user.getTrainingSession;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for retrieving training sessions by name.
 * <p>
 * This record encapsulates the name of the training session to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String sessionName = "Session 1";
 *
 * GetTrainingSessionsByNameRequest request = new GetTrainingSessionsByNameRequest(sessionName);
 * }</pre>
 *
 * @param name The name of the training session to be retrieved.
 */
public record GetTrainingSessionsByNameRequest(

        @NotNull(message = "error.trainingSession.name.null")
        @StringValid(entity = "trainingSession", field = "name", min = 3)
        String name

) {
}
