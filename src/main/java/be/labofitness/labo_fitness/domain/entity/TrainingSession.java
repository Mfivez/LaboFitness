package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDateTime start_date;

    @Column(name = "end_date",nullable = true)
    private LocalDateTime end_date;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "recomended_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private RecommendedLevel recommended_level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Coach coach;


    public TrainingSession() {}
}
