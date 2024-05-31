package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.dal.util.HasGetIdMethod;
import be.labofitness.labo_fitness.domain.enums.Goal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a {@code Client} entity.
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "clients")
public class Client extends User implements HasGetIdMethod {

    /**
     * The weight of the client.
     */
    @Column(name = "weight", nullable = false)
    private int weight;

    /**
     * The height of the client.
     */
    @Column(name = "height", nullable = false)
    private int height;

    /**
     * The goal of the client.
     * <p>Possible values are defined in the {@link Goal} enum.</p>
     */
    @Column(name = "goal", nullable = true)
    @Enumerated(EnumType.STRING)
    private Goal goal;

    /**
     * The lifestyle factor of the client.
     */
    @Column(name = "life_style", nullable = true)
    private double lifeStyle;

    /**
     * The list of {@link Competition} in which the client participates.
     * <p>FetchType is EAGER to load competitions immediately with the client.</p>
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name ="clients_competitions",
            joinColumns = @JoinColumn(name = "id_client",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "id_competition",nullable = true))
    private List<Competition> competitions;

    /**
     * The list of {@link TrainingSession} to which the {@link Client} is subscribed.
     * <p>FetchType is EAGER to load training sessions immediately with the {@link Client}.</p>
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_trainings_subscriptions",
                joinColumns = @JoinColumn(name = "id_client",nullable = true),
                inverseJoinColumns = @JoinColumn(name = "id_training_sessions",nullable = true))
    private List<TrainingSession> trainingSessions;

    /**
     * Default constructor initializing empty lists for {@link Competition} and {@link TrainingSession}.
     */
    public Client() {
        this.competitions = new ArrayList<>();
        this.trainingSessions = new ArrayList<>();
    }

}
