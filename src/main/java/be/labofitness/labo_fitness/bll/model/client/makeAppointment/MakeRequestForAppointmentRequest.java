package be.labofitness.labo_fitness.bll.model.client.makeAppointment;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for making an appointment by a client.
 * <p>
 * This record encapsulates the reason for the appointment and the email of the physiotherapist.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String reason = "I need a physiotherapy session for my back pain.";
 * String email = "physiotherapist@example.com";
 * MakeRequestForAppointmentRequest request = new MakeRequestForAppointmentRequest(reason, email);
 * }</pre>
 *
 * @param reasonOfAppointment      The reason for the appointment.
 * @param physiotherapistEmail     The email of the physiotherapist.
 */
public record MakeRequestForAppointmentRequest(

        @NotNull(message = "error.client.reasonOfAppointment.null")
        @StringValid(entity = "appointment", field = "reasonOfAppointment", min = 20)
        String reasonOfAppointment,

        @NotNull(message = "error.client.physiotherapistEmail.null")
        @EmailValid
        String physiotherapistEmail

) {
}