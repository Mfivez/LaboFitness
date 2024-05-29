package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Represents an {@code Appointment} between a {@link Client} and a {@link Physiotherapist}.
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity<Long> {

    /**
     * The name of the appointment.
     */
    @Column(name ="name", nullable = true)
    private String name;

    /**
     * The price of the appointment.
     */
    @Column(name = "price", nullable = true)
    private int price;

    /**
     * The start date and time of the appointment.
     */
    @Column(name = "appointment_date", nullable = true)
    private LocalDateTime startDate;

    /**
     * The end date and time of the appointment.
     */
    @Column(name="end_date", nullable = true)
    private LocalDateTime endDate;

    /**
     * The {@link Client} who has the appointment.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    /**
     * The {@link Physiotherapist} assigned to the {@code appointment}.
     * <p>Fetch type is EAGER, meaning the physiotherapist data is loaded immediately.</p>
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Physiotherapist physiotherapist;

    /**
     * Indicates whether the appointment is canceled by the {@link Client} or the {@link Physiotherapist}.
     */
    @Column(name = "is_cancel")
    private boolean isCancel;

    /**
     * <p>Indicates whether the appointment is planned.</p>
     * <p>An appointment is considered planned (IsPlanned = true)
     * when a {@link Physiotherapist} and their {@link Client} agree on a specific time for the {@code Appointment}.
     */
    @Column(name = "is_planned")
    private boolean IsPlanned;

    /**
     * <p>The status of the appointment, which depends on the state of the request:</p>
     * <p>- {@code NOT_PLANNED}: The appointment is not yet planned by the {@link Physiotherapist}.</p>
     * <p>- {@code NO_RESPONSE}: No response has been received yet by the {@link Client}.</p>
     * <p>- {@code ACCEPTED}: The appointment request has been accepted by the {@link Client}.</p>
     * <p>- {@code REFUSED}: The appointment request has been refused by the {@link Client}.</p>
     * <p>- {@code PENDING}: The appointment request is pending.</p>
     * <p>- {@code PENDING_DELETE_BY_PHYSIO}: The appointment is pending deletion by the {@link Physiotherapist}.</p>
     * <p>- {@code CANCELLED}: The appointment has been cancelled.</p>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatus appointmentStatus;

    /**
     * The description of the appointment status response.
     */
    @Column(name = "appointment_status_response_description")
    private String appointmentStatusResponseDescription;

    /**
     * The reason for the appointment.
     */
    @Column(name = "reason_of_appointment", nullable = false)
    private String reasonOfAppointment;

    /**
     * Default constructor initializing default values.
     */
    public Appointment() {
        this.isCancel = false;
        this.IsPlanned = false;
        this.appointmentStatus = AppointmentStatus.NOT_PLANNED; // TODO Ajouter autre chose que NO_RESPONSE (NEW PARAM)
    }

    public Appointment(Client client, Physiotherapist physiotherapist, String reason, AppointmentStatus appointmentStatus) {
        this.client = client;
        this.reasonOfAppointment = reason;
        this.appointmentStatus = appointmentStatus;
        this.physiotherapist = physiotherapist;
        this.isCancel = false;
        this.IsPlanned = false;
    }

}
