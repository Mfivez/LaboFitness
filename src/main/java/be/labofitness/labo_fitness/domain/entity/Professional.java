package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import com.fasterxml.jackson.annotation.JsonKey;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
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


    public Professional() {
        locationPlace = new HashSet<>();
    }

//    public Professional(String name, String last_name, String email, String password, Adress adress, boolean isActive, Set<Role> roles, String specialization, Set<LocationPlace> locationPlace) {
//        super(name, last_name, email, password, adress, isActive, roles);
//        this.specialization = specialization;
//        this.locationPlace = locationPlace;
//    }


//    public Professional(String name, String lastName, String email, String password, Adress adress, Set<Role> roles, String specialization) {
//        super(name, lastName, email, password, adress, roles);
//        this.specialization = specialization;
//    }
}
