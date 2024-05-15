package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "accreditations")
public class Accreditation extends BaseEntity<Long> {

    @Getter @Setter
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccreditationType type;

    @Getter @Setter
    @Column(name = "description", nullable = false)
    private String description;



    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Professional professional;


}
