package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity @Table(name = "reports")
@Getter @Setter @ToString
public class Report extends BaseEntity<Long> {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name ="id_reported_user",nullable = false)
    private User reportedUser;

    @ManyToOne
    @JoinColumn(name = "id_complainant_user",nullable = false)
    private User complainant;

    @Column(name = "is_confirme", nullable = false)
    private boolean isConfirmed;

    //BUG GITHUB
    public Report() {
        this.isConfirmed = false;
    }
}
