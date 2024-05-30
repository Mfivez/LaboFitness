package be.labofitness.labo_fitness.bll.model.user.getTrainingSession;
import jakarta.validation.constraints.Min;

/**
 * Represents the request model for retrieving training sessions by duration.
 * <p>
 * This record encapsulates the minimum duration of the training sessions to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * int duration = 60;
 *
 * GetTrainingSessionsByDurationRequest request = new GetTrainingSessionsByDurationRequest(duration);
 * }</pre>
 *
 * @param duration The minimum duration of the training sessions to be retrieved.
 */
public record GetTrainingSessionsByDurationRequest(
        @Min(value = 1, message = "Duration must be at least 1")
        int duration
) {
}
