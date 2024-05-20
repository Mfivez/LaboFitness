package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import com.fasterxml.jackson.annotation.JsonKey;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Table(name = "professionals")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @ToString
public abstract class Professional extends User{

    @Column(name = "specialization")
    private String specialization;


    @Column(name = "work_schedule", nullable = true)
    private String workSchedule;


    /**
     * Pour cette relation, nous passons en unidirectionnelle, car on se moque de savoir depuis location place, à qui appartient le bâtiment.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "professional_locations_places",
                joinColumns = @JoinColumn(name="id_professional", nullable = true),
                inverseJoinColumns = @JoinColumn(name = "id_location_place"))
    private Set<LocationPlace> locationPlace;


    public Professional() {
        locationPlace = new HashSet<>();
    }
}
