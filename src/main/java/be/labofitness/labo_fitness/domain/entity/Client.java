package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.enums.Goal;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "clients")
public class Client extends User {

    @Getter @Setter
    @Column(name = "weight", nullable = false)
    private int weight;

    @Getter @Setter
    @Column(name = "height", nullable = false)
    private int height;

    @Getter @Setter
    @Column(name = "goal", nullable = false)
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Getter @Setter
    @Column(name = "lifeStyle", nullable = false)
    private double lifeStyle;

    @Getter @Setter
    @Column(name = "age", nullable = false)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name ="clients_competitions",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_competition")
    )
    private List<Competition> competitions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_trainings_subscriptions",
                joinColumns = @JoinColumn(name = "id_client"),
                inverseJoinColumns = @JoinColumn(name = "id_training_sessions"))
    private List<TrainingSession> trainingSessions;

}
