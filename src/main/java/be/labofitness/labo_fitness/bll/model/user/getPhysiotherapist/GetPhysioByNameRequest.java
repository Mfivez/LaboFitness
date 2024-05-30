package be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for retrieving physiotherapists by name.
 * <p>
 * This record encapsulates the name of the physiotherapist to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String physioName = "John";
 *
 * GetPhysioByNameRequest request = new GetPhysioByNameRequest(physioName);
 * }</pre>
 *
 * @param name The name of the physiotherapist to be retrieved.
 */
public record GetPhysioByNameRequest(

        @NotNull(message = "error.physiotherapist.name.null")
        @NotBlank(message = "error.physiotherapist.name.blank")
        String name
)
{}
