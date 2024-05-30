package be.labofitness.labo_fitness.domain.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a {@code professional} entity in the system.
 * <br>Extends the {@link User} class, inheriting its properties.
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "professionals")
public abstract class Professional extends User{

    /**
     * The specialization of the {@code professional}.
     */
    @Column(name = "specialization")
    private String specialization;

    /**
     * The work schedule of the {@code professional}.
     */
    @Column(name = "work_schedule", nullable = true)
    private String workSchedule;

    /**
     * The set of {@link LocationPlace} associated with the professional.
     * <br>It is marked as {@code FetchType.LAZY} to indicate that the locations are lazily loaded.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "professional_locations_places",
                joinColumns = @JoinColumn(name="id_professional", nullable = true),
                inverseJoinColumns = @JoinColumn(name = "id_location_place"))
    private Set<LocationPlace> locationPlace;

    /**
     * Constructs a new {@code Professional} object.
     * <br>Initializes the {@link LocationPlace} set to an empty HashSet.
     */
    public Professional() {
        locationPlace = new HashSet<>();
    }

}
