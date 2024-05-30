package be.labofitness.labo_fitness.bll.model.user.getCoach;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for retrieving coaches based on whether they offer remote services.
 * <p>
 * This record encapsulates a boolean flag indicating whether the coach offers remote services.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * boolean isRemote = true;
 *
 * GetCoachesByRemoteRequest request = new GetCoachesByRemoteRequest(isRemote);
 * }</pre>
 *
 * @param is_remote A boolean flag indicating whether the coach offers remote services.
 */
public record GetCoachesByRemoteRequest(

        @NotNull(message = "error.coach.isRemote.null")
        @NotBlank(message = "error.coach.isRemote.blank")
        boolean is_remote
        )
{
}
