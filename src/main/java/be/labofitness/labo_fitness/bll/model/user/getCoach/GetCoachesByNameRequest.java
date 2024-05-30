package be.labofitness.labo_fitness.bll.model.user.getCoach;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for retrieving coaches by name.
 * <p>
 * This record encapsulates the name of the coach to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String coachName = "John";
 *
 * GetCoachesByNameRequest request = new GetCoachesByNameRequest(coachName);
 * }</pre>
 *
 * @param name The name of the coach to be retrieved.
 */
public record GetCoachesByNameRequest(

        @NotNull(message = "error.coach.name.null")
        @StringValid(entity = "coach", field = "name", min = 2)
        String name

)
{}
