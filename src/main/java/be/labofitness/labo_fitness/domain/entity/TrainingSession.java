package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a {@code training session} entity in the system.
 * <br>{@code Training sessions} are organized events with specific details
 * and are conducted by coaches.
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name ="training_session")
public class TrainingSession extends BaseEntity<Long> {

    /**
     * The name of the {@code training session}
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The duration of the {@code training session} in minute.
     */
    @Column(name = "duration", nullable = false)
    private int duration;

    /**
     * The start date and time of the {@code training session}.
     */
    @Column(name = "start_date",nullable = true)
    private LocalDateTime startDate;

    /**
     * The end date and time of the {@code training session}.
     */
    @Column(name = "end_date",nullable = true)
    private LocalDateTime endDate;

    /**
     * The description of the {@code training session}.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Indicates whether the inscription for the {@code training session} is open.
     */
    @Column(name = "is_inscription_open", nullable = false)
    private boolean isInscriptionOpen;

    /**
     * The {@link RecommendedLevel} for participants of the {@code training session}.
     */
    @Column(name = "recommended_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private RecommendedLevel recommendedLevel;

    /**
     * The {@link Coach} conducting the {@code training session}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Coach coach;

    /**
     * The {@link Client} who are enrolled in the {@code training session}.
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "trainingSessions")
    private Set<Client> clients;

    /**
     * Constructs a {@code training session} with default values.
     * <br>The inscription is set to closed by default.
     */
    public TrainingSession() {
        this.isInscriptionOpen = false;
    }

}
