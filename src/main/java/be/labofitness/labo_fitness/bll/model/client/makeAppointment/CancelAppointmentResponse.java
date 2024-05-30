package be.labofitness.labo_fitness.bll.model.client.makeAppointment;

/**
 * Represents the response model for canceling an appointment by a client.
 * <p>
 * This record encapsulates a message indicating the success of the cancellation operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String successMessage = "Appointment successfully canceled.";
 * CancelAppointmentResponse response = new CancelAppointmentResponse(successMessage);
 * }</pre>
 *
 * @param message A message indicating the success of the cancellation operation.
 */
public record CancelAppointmentResponse(
        String message
) {
}