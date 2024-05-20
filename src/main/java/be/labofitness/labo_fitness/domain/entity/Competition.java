package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="competitions")
@Getter @Setter
@AllArgsConstructor
public class Competition extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "sports_competitions",
                joinColumns = @JoinColumn(name = "competition_id", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "sport_id", nullable = false))
    private Set<Sport> sports;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Coach coach;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "competitions")
    private Set<Client> client;

    public Competition() {
        this.client = new HashSet<>();
        this.sports = new HashSet<>();
    }
    //BUG GITHUB
}
