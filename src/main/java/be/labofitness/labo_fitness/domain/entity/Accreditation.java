package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an {@link Accreditation} entity.
 * <p>This entity extends {@link BaseEntity} with a Long type identifier.</p>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "accreditations")
public class Accreditation extends BaseEntity<Long> {

    /**
     * The type of accreditation.
     * <p>This field is mandatory and uses the {@link AccreditationType} enumeration.</p>
     */
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccreditationType type;

    /**
     * The description of the accreditation.
     * <p>This field is mandatory.</p>
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The professional associated with this accreditation.
     *
     * <p>The {@code fetch = FetchType.EAGER} parameter indicates that the associated {@link Professional}
     * entity will be loaded immediately along with the {@link Accreditation} entity. This means that when
     * an {@link Accreditation} entity is fetched from the database, its associated {@link Professional} entity
     * is also {@code fetched in the same query}, ensuring that the {@link Professional} data is readily available.
     *
     * <p>This is useful when the associated {@link Professional} is frequently accessed alongside the
     * {@link Accreditation}, as it avoids additional database queries and can improve performance in such scenarios.
     * However, it can also lead to performance overhead if the {@link Professional} entity is large or not always
     * needed, as it increases the amount of data loaded into memory.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Professional professional;

}
