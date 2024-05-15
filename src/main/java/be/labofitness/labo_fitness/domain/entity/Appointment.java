package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "appointments")
public class Appointment extends BaseEntity<Long> {

    @Getter @Setter
    @Column(name = "price", nullable = false)
    private int price;

    @Getter @Setter
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @Getter @Setter
    private Client client;

    @ManyToOne
    @Getter @Setter
    private Physiotherapist physiotherapist;

}
