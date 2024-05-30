package be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist;
import jakarta.validation.constraints.Pattern;

/**
 * Represents the request model for retrieving physiotherapists by specialization.
 * <p>
 * This record encapsulates the specialization of the physiotherapist to be retrieved.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String specialization = "Kine";
 *
 * GetPhysioBySpecializationRequest request = new GetPhysioBySpecializationRequest(specialization);
 * }</pre>
 *
 * @param specialization The specialization of the physiotherapist to be retrieved.
 */
public record GetPhysioBySpecializationRequest(
        @Pattern(regexp = "^(Kine)$", message = "specialization must be 'Kine'")
        String specialization
)
{}