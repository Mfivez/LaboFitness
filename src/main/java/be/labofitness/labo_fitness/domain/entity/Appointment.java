package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity @Table(name = "appointments")
@Getter @Setter @ToString
public class Appointment extends BaseEntity<Long> {

    @Column(name = "price", nullable = true)
    private int price;

    @Column(name = "appointment_date", nullable = true)
    private LocalDateTime AppointmentDate;

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