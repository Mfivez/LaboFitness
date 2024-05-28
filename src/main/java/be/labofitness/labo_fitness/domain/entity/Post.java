package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a {@code post} entity in the system.
 * <br>Extends the {@link BaseEntity} class, inheriting its properties.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name ="posts")
public class Post extends BaseEntity<Long> {

    /**
     * The name of the {@code post}.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the {@code post}.
     */
    @Column(name = "description",nullable = false)
    private String description;

    /**
     * The appointment date associated with the post.
     * <br>It is marked as {@code nullable = true} because an appointment date may not always be specified for a post.
     */
    @Column(name = "appointment_date",nullable = true)
    private LocalDateTime appointmentDate;

    /**
     * The {@link Sport} associated with the post.
     * <br>It is annotated with {@link ManyToOne} and {@link JoinColumn} to specify the database mapping.
     * <br>It is marked as {@code nullable = false} to indicate that a sport is required for all posts.
     */
    @ManyToOne
    @JoinColumn(name = "sport",unique = true, nullable = false)
    private Sport sport;

    /**
     * The {@link User} who created the post.
     * <br>It is annotated with {@link ManyToOne} and {@link JoinColumn} to specify the database mapping.
     */
    @ManyToOne
    @JoinColumn(name = "post_creator", nullable = false)
    private User creator;

}
