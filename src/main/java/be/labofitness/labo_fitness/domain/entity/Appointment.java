package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity @Table(name = "appointments")
@Getter @Setter @ToString
public class Appointment extends BaseEntity<Long> {

    @Column(name ="name", nullable = true)
    private String name;

    @Column(name = "price", nullable = true)
    private int price;

    @Column(name = "appointment_date", nullable = true)
    private LocalDateTime startDate;

    @Column(name="end_date", nullable = true)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Physiotherapist physiotherapist;

    @Column(name = "is_cancel")
    private boolean isCancel;

    @Column(name = "is_plannified")
    private boolean isPlannified;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatus appointmentStatus;

    @Column(name = "appointment_status_response_description")
    private String appointmentStatusResponseDescription;

    @Column(name = "reason_of_appointment", nullable = false)
    private String reasonOfAppointment;

    //**** CONSTRUCTOR ****
    public Appointment() {
        this.isCancel = false;
        this.isPlannified = false;
        this.appointmentStatus = AppointmentStatus.NOT_PLANNED; // TODO Ajouter autre chose que NO_RESPONSE (NEW PARAM)
    }
}
