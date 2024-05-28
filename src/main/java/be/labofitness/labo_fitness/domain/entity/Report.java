package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Represents a {@code report} entity, which is used to report inappropriate behavior or content.
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "reports")
public class Report extends BaseEntity<Long> {

    /**
     * The description of the {@code report}.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The date when the {@code report} was submitted.
     */
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    /**
     * The user who is being {@code report}.
     */
    @ManyToOne
    @JoinColumn(name ="id_reported_user",nullable = false)
    private User reportedUser;

    /**
     * The user who submitted the {@code report}.
     */
    @ManyToOne
    @JoinColumn(name = "id_complainant_user",nullable = false)
    private User complainant;

    /**
     * Indicates whether the {@code report} has been approved by the {@code moderation team}.
     */
    @Column(name = "is_approved", nullable = false)
    private boolean isApproved;

    /**
     * Indicates whether the {@code report} has been confirmed by the {@code admin team}.
     */
    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;

    /**
     * Default constructor initializing the confirmation and approval status to false.
     */
    public Report() {
        this.isConfirmed = false;
        this.isApproved = false;
    }
}
