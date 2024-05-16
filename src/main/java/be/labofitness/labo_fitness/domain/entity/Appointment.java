package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "appointments")
@Getter @Setter @ToString
public class Appointment extends BaseEntity<Long> {

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Physiotherapist physiotherapist;

}
