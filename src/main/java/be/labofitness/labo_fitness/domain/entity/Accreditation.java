package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
