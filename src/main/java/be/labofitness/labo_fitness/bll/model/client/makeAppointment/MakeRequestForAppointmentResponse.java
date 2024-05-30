package be.labofitness.labo_fitness.bll.model.client.makeAppointment;

/**
 * Represents the response model for making an appointment request by a client.
 * <p>
 * This record encapsulates a message indicating whether the appointment request is validated.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String message = "Your appointment request has been validated.";
 * MakeRequestForAppointmentResponse response = new MakeRequestForAppointmentResponse(message);
 * }</pre>
 *
 * @param AppointmentValidated A message indicating whether the appointment request is validated.
 */
public record MakeRequestForAppointmentResponse(
        String AppointmentValidated
) {
}