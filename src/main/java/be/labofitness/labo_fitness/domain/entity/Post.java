package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name ="posts")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Post extends BaseEntity<Long> {
    //TODO (in progress)

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "appointment_date",nullable = true)
    private LocalDateTime appointmentDate;


    @ManyToOne
    @JoinColumn(name = "sport",unique = true, nullable = false)
    private Sport sport;

    //BUG GITHUB
    @ManyToOne
    @JoinColumn(name = "post_creator", nullable = false)
    private User creator;

}
