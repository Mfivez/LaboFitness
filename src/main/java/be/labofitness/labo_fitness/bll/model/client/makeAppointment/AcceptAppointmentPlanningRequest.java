package be.labofitness.labo_fitness.bll.model.client.makeAppointment;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import be.labofitness.labo_fitness.il.utils.annotations.appointmentValidator.AppointmentStatusValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for accepting an appointment planning by a client.
 * <p>
 * This record encapsulates the ID of the appointment, its status, and a description.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Long appointmentId = 123L;
 * AppointmentStatus status = AppointmentStatus.ACCEPTED;
 * String description = "Appointment accepted with confirmation.";
 * AcceptAppointmentPlanningRequest request = new AcceptAppointmentPlanningRequest(appointmentId, status, description);
 * }</pre>
 *
 * @param id          The ID of the appointment.
 * @param status      The status of the appointment.
 * @param description The description related to the appointment.
 */
public record AcceptAppointmentPlanningRequest(

        @NotNull(message = "error.client.id.null")
        @NotBlank(message = "error.client.id.blank")
        @Min(value = 1)
        Long id,

        @NotNull(message = "error.client.status.null")
        @AppointmentStatusValid
        AppointmentStatus status,

        @NotNull(message = "error.client.description.null")
        @StringValid( entity = "Appointment", field = "ResponseDetail", min = 10)
        String description
) {
}