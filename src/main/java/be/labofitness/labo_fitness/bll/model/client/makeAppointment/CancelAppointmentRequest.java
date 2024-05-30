package be.labofitness.labo_fitness.bll.model.client.makeAppointment;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import be.labofitness.labo_fitness.il.utils.annotations.appointmentValidator.AppointmentStatusValid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for canceling an appointment by a client.
 * <p>
 * This record encapsulates the ID of the appointment, the new status, and a boolean indicating cancellation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Long appointmentId = 123L;
 * AppointmentStatus status = AppointmentStatus.CANCELED;
 * boolean isCancelled = true;
 * CancelAppointmentRequest request = new CancelAppointmentRequest(appointmentId, status, isCancelled);
 * }</pre>
 *
 * @param id        The ID of the appointment to cancel.
 * @param status    The new status of the appointment.
 * @param isCancel  A boolean indicating if the appointment is canceled.
 */
public record CancelAppointmentRequest(

        @NotNull(message = "error.client.appointment.null")
        @NotBlank(message = "error.client.appointment.blank")
        @Min(value = 1)
        Long id,

        @NotNull(message = "error.client.status.null")
        @AppointmentStatusValid
        AppointmentStatus status,

        @NotNull(message = "error.client.isCancel.null")
        @NotBlank(message = "error.client.isCancel.blank")
        boolean isCancel
) {
}
