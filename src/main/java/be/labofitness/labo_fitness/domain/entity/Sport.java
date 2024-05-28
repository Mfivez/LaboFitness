package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.TypeSport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a {@code sport} entity in the system.
 * <br>{@code Sports} can be associated with various activities and events.
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "sports")
public class Sport extends BaseEntity<Long> {

    /**
     * The name of the {@code sport}.
     */
    @Column(name = "name",nullable = false)
    private String name;

    /**
     * The description of the {@code sport}.
     */
    @Column(name = "description",nullable = true)
    private String description;

    /**
     * The type of the {@code sport}.
     */
    @Column(name = "type", nullable = true)
    @Enumerated(EnumType.STRING)
    private TypeSport typeSport;

    /**
     * Constructs a {@code sport} with default values.
     * <br>The type is set to {@code INDEFINITE}.
     */
    public Sport() {
        this.typeSport = TypeSport.INDEFINITE;
    }

}
