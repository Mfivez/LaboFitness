package be.labofitness.labo_fitness.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Table(name = "physiotherapists")
public class Physiotherapist extends Professional{


    @Column(name = "inami_number", nullable = false)
    private Integer inami_number;


}
