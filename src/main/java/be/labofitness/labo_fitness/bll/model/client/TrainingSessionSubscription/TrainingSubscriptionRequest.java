package be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for subscribing to a training session.
 * <p>
 * This record encapsulates the ID of the training session.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Long sessionId = 123L;
 * TrainingSubscriptionRequest request = new TrainingSubscriptionRequest(sessionId);
 * }</pre>
 *
 * @param id The ID of the training session.
 */
public record TrainingSubscriptionRequest(

        @NotNull(message = "error.trainingSession.id.null")
        @NotBlank(message = "error.trainingSession.id.blank")
        @Min(value = 1)
        Long id

) {
}
