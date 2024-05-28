package be.labofitness.labo_fitness.domain.entity.base;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Represents a {@code base entity} class serving as a superclass for other entities. </p>
 *
 * <p>It provides common attributes such as an identifier and timestamps for creation and update.</p>
 *
 * <p>The identifier type {@code T} must implement the {@link java.io.Serializable} interface
 * to ensure that instances of this class can be serialized into a stream of bytes for
 * storage or transmission.</p>
 *
 * @param <T> The type of the identifier, which must implement {@link java.io.Serializable}.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @ToString
@MappedSuperclass
public class BaseEntity <T extends Serializable> {

    /**
     * The unique identifier for the entity.
     */
    @Id
    @GeneratedValue
    private T id;

    /**
     * The timestamp indicating when the entity was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Setter
    private LocalDateTime createdAt;

    /**
     * The timestamp indicating when the entity was last updated.
     */
    @Column(name = "updated_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Setter
    private LocalDateTime updatedAt;
}
