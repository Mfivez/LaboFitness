package be.labofitness.labo_fitness.domain.entity;


import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "location_places")
@Getter @Setter @ToString
public class LocationPlace extends BaseEntity<Long> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "location_place_city")),
            @AttributeOverride(name = "number", column = @Column(name = "location_place_number")),
            @AttributeOverride(name = "street", column = @Column(name = "location_place_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "location_place_zipcode"))
    })
    private Adress adress;
    //BUG GITHUB
}
