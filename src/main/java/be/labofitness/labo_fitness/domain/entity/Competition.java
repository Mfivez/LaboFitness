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

    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Getter @Setter
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "sports_competitions",
                joinColumns = @JoinColumn(name = "competition_id"),
                inverseJoinColumns = @JoinColumn(name = "sport_id"))
    private Set<Sport> sports;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Coach coach;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "competitions")
    private Set<Client> client;

    public Competition() {
        this.client = new HashSet<>();
    }
}
