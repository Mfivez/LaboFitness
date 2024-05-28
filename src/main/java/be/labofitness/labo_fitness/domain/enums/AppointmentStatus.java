package be.labofitness.labo_fitness.domain.enums;
import be.labofitness.labo_fitness.domain.entity.Appointment;

/**
 * Enum representing the status of an {@link Appointment}.
 */
public enum AppointmentStatus {

    /**
     * NOT_PLANNED: The appointment is not yet planned.
     */
    NOT_PLANNED,

    /**
     *NO_RESPONSE: No response has been received regarding the appointment.
     */
    NO_RESPONSE,

    /**
     *ACCEPTED: The appointment has been accepted.
     */
    ACCEPTED,

    /**
     *REFUSED: The appointment has been refused.
     */
    REFUSED,

    /**
     *PENDING: The appointment is pending.
     */
    PENDING,

    /**
     *PENDING_DELETE_BY_PHYSIO: The appointment is pending deletion by the physiotherapist.
     */
    PENDING_DELETE_BY_PHYSIO,

    /**
     *CANCELLED: The appointment has been cancelled.
     */
    CANCELLED
}