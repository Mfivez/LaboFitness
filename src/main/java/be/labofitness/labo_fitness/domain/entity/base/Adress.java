package be.labofitness.labo_fitness.domain.entity.base;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

    private String street;

    private String number;

    private String city;

    private String zipcode;
}
