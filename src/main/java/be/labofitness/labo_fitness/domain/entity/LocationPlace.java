package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a {@code location place} entity in the system.
 * <p>Extends the {@link BaseEntity} class, inheriting its properties.</p>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "location_places")
public class LocationPlace extends BaseEntity<Long> {

    /**
     * The {@link Address} associated with the {@code location place}.
     * <p>Contains details such as {@code city}, {@code street}, {@code number}, and {@code ZIP code}.</p>
     *
     * <p>This field is annotated with {@link Embedded}, indicating that the{@link Address} object
     * will be stored as a part of this entity's table, rather than in a separate table.</p>
     *
     * <p>The {@link AttributeOverrides} annotation allows for customizing the column names
     * of the embedded {@link Address} entity. <br>In this case, it overrides the default column names
     * with more descriptive ones: {@code city}, {@code street}, {@code number}, and {@code ZIP code}.</p>
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "location_place_city")),
            @AttributeOverride(name = "number", column = @Column(name = "location_place_number")),
            @AttributeOverride(name = "street", column = @Column(name = "location_place_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "location_place_zipcode"))
    })
    private Address address;

}
