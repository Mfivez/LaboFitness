package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.TypeSport;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Entity @Table(name = "sports")
@Getter @Setter @ToString
public class Sport extends BaseEntity<Long> {

    @Column(name = "name",nullable = false)
    private String name;


    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "type", nullable = true)
    @Enumerated(EnumType.STRING)
    private TypeSport typeSport;


    public Sport() {
        this.typeSport = TypeSport.INDEFINI;
    }

}
