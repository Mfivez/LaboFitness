package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.TypeSport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Entity @Table(name = "sports")
public class Sport extends BaseEntity<Long> {

    @Column(name = "name",nullable = false)
    @Getter @Setter
    private String name;


    @Column(name = "description",nullable = true)
    @Getter @Setter
    private String description;

    @Column(name = "type", nullable = true)
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private TypeSport typeSport;

    public Sport() {
        this.typeSport = TypeSport.INDEFINI;
    }

}
