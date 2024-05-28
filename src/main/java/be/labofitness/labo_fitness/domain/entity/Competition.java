package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a {@code Competition} in the system.
 * <p>Extends the {@link BaseEntity} class, inheriting its properties.</p>
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name ="competitions")
public class Competition extends BaseEntity<Long> {

    /**
     * A unique identifier for the {@code competition}'s name.
     * <p>Used for internal identification purposes.</p>
     */
    @Column(name = "name_identifier", unique = true, nullable = false)
    private String competitionNameIdentifier;

    /**
     * The name of the {@code competition}.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The start date of the {@code competition}.
     */
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    /**
     * The end date of the {@code competition}.
     */
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    /**
     * <p>Indicates whether the {@code competition}'s inscription is open.</p>
     * <p>1. {@code True}: participants can register for the competition</p>
     * <p>2. {@code False}: registration is closed.</p>
     */
    @Column(name = "is_inscription_open", nullable = false)
    private boolean isInscriptionOpen;

    /**
     * The set of {@link Sport} associated with the {@code competition}.
     * <p>Represents the {@link Sport} disciplines included in the {@code competition}.</p>
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "sports_competitions",
                joinColumns = @JoinColumn(name = "competition_id", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "sport_id", nullable = false))
    private Set<Sport> sports;

    /**
     * The {@link Coach} associated with the {@code competition}.
     * <p>Represents the {@link Coach} responsible for organizing and managing the {@code competition}.</p>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Coach coach;

    /**
     * The set of {@link Client} registered for the {@code competition}.
     * Represents the {@link Client} who have registered to participate in the {@code competition}.
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "competitions")
    private Set<Client> client;

    /**
     * <p>Default constructor initializing : </p>
     * <p>1. set of {@link Client} to empty sets</p>
     * <p>2. set of {@link Sport} to empty sports</p>
     * <p>3. set {@code isInscriptionOpen} to {@code false} by default</p>
     */
    public Competition() {
        this.client = new HashSet<>();
        this.sports = new HashSet<>();
        this.isInscriptionOpen = false;
    }

}
