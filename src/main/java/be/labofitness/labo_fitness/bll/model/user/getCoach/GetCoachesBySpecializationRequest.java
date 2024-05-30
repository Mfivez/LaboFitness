package be.labofitness.labo_fitness.bll.model.user.getCoach;
import jakarta.validation.constraints.Pattern;

/**
 * Represents the request model for retrieving coaches by specialization.
 * <p>
 * This record encapsulates the specialization of the coach to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String specialization = "Yoga";
 *
 * GetCoachesBySpecializationRequest request = new GetCoachesBySpecializationRequest(specialization);
 * }</pre>
 *
 * @param specialization The specialization of the coach to be retrieved.
 */
public record GetCoachesBySpecializationRequest(

        @Pattern(regexp = "^(Yoga|Fitness)$", message = "specialization must be 'Yoga' or 'Fitness'")
        String specialization
)
{}

