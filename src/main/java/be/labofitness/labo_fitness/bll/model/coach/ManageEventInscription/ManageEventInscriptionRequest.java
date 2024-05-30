package be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for managing event inscription in the coach module.
 * <p>
 * This record encapsulates the ID of the event and its state.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Long eventId = 123L;
 * boolean state = true;
 * ManageEventInscriptionRequest request = new ManageEventInscriptionRequest(eventId, state);
 * }</pre>
 *
 * @param id    The ID of the event.
 * @param state The state of the event.
 */
public record ManageEventInscriptionRequest(

        @NotNull(message = "error.event.id.null")
        @NotBlank(message = "error.event.id.blank")
        @Min(value = 1)
        Long id,

        @NotNull(message = "error.event.state.null")
        @NotBlank(message = "error.event.state.blank")
        boolean state

) {
}
