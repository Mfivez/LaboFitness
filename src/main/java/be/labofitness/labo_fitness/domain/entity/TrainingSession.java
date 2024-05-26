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

@Entity
@Table(name ="training_session")
@AllArgsConstructor
@Getter @Setter @ToString
public class TrainingSession extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "start_date",nullable = true)
    private LocalDateTime startDate;

    @Column(name = "end_date",nullable = true)
    private LocalDateTime endDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_inscription_open", nullable = false)
    private boolean isInscriptionOpen;

    @Column(name = "recomended_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private RecommendedLevel recommended_level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Coach coach;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "trainingSessions")
    private Set<Client> clients;



    public TrainingSession() {
        this.isInscriptionOpen = false;
    }
}
