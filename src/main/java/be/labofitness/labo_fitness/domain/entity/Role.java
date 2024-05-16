package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@AllArgsConstructor
@Table (name = "roles")
@Getter @Setter @ToString
public class Role extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Role(String description) {
        this.description = description;
    }

    public Role() {
        this.name = "user";
    }

}
