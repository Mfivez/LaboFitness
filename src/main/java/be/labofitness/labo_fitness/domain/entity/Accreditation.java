package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "accreditations")
@Getter @Setter @ToString
public class Accreditation extends BaseEntity<Long> {

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccreditationType type;

    @Column(name = "description", nullable = false)
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Professional professional;


}
