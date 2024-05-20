package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.enums.Goal;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity @Table(name = "clients")
@Getter @Setter @ToString
public class Client extends User {

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "goal", nullable = true)
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column(name = "lifeStyle", nullable = true)
    private double lifeStyle;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name ="clients_competitions",
            joinColumns = @JoinColumn(name = "id_client",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "id_competition",nullable = true))
    private List<Competition> competitions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_trainings_subscriptions",
                joinColumns = @JoinColumn(name = "id_client",nullable = true),
                inverseJoinColumns = @JoinColumn(name = "id_training_sessions",nullable = true))
    private List<TrainingSession> trainingSessions;

    //**** CONSTRUCTOR ****
    public Client() {
        this.competitions = new ArrayList<>();
        this.trainingSessions = new ArrayList<>();
    }
    //BUG GITHUB



}
