package be.labofitness.labo_fitness.bll.model.client.makeAppointment;

/**
 * Represents the response model for accepting an appointment planning by a client.
 * <p>
 * This record encapsulates a response message.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String responseMessage = "Appointment planning accepted.";
 * AcceptAppointmentPlanningResponse response = new AcceptAppointmentPlanningResponse(responseMessage);
 * }</pre>
 *
 * @param response The response message.
 */
public record AcceptAppointmentPlanningResponse(
        String response
) {
}