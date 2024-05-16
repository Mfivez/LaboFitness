package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="training_session")
@AllArgsConstructor
public class TrainingSession extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "duration", nullable = false)
    @Getter @Setter
    private int duration;

    @Column(name = "start_date",nullable = true)
    @Getter @Setter
    private LocalDateTime start_date;

    @Column(name = "end_date",nullable = true)
    @Getter @Setter
    private LocalDateTime end_date;

    @Column(name = "description", nullable = false)
    @Getter @Setter
    private String description;

    @Column(name = "recomended_level", nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private RecommendedLevel recommended_level;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "trainingSessions")
    private List<Client> clientSubscriber;


    public TrainingSession() {
        this.clientSubscriber = new ArrayList<>();
    }
}
