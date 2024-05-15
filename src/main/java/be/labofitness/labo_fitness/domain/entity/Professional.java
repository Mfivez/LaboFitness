package be.labofitness.labo_fitness.domain.entity;

import com.fasterxml.jackson.annotation.JsonKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professionals")
@Inheritance(strategy = InheritanceType.JOINED)
public class Professional extends User{

    @Getter @Setter
    @Column(name = "specialization")
    private String specialization;


    /**
     * Pour cette relation, nous passons en unidirectionnelle, car on se moque de savoir depuis location place, à qui appartient le bâtiment.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "professional_locations_places",
                joinColumns = @JoinColumn(name="id_professional"),
                inverseJoinColumns = @JoinColumn(name = "id_location_place"))
    private Set<LocationPlace> locationPlace;


}
