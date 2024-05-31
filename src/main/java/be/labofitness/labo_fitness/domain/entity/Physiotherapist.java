package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.dal.util.HasGetIdMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * Represents a {@code physiotherapist} entity in the system.
 * <br>Extends the {@link Professional} class, inheriting its properties.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "physiotherapists")
public class Physiotherapist extends Professional implements HasGetIdMethod {

    /**
     * <p>The {@code INAMI number} associated with the {@code physiotherapist}.</p>
     *
     * <p>{@code INAMI (Institut National d'Assurance Maladie-Invalidité)} is a unique identification number
     * assigned to healthcare professionals in Belgium.</p>
     *
     * <p>This field is annotated with {@link Column}, specifying its database column properties.
     * <br>It is marked as {@code nullable = false} to indicate that the {@code INAMI number} is required for all {@code physiotherapists}.</p>
     */
    @Column(name = "inami_number", nullable = false)
    private long inamiNumber;

}
