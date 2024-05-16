package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@AllArgsConstructor
@Table (name = "roles")
public class Role extends BaseEntity<Long> {

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @Column(name = "description")
    private String description;

    public Role(String description) {
        this.description = description;
    }

    public Role() {
        this.name = "user";
    }

}
