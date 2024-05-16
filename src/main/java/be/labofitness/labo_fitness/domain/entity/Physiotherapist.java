package be.labofitness.labo_fitness.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "physiotherapists")

public class Physiotherapist extends Professional{


    @Column(name = "inami_number")
    @Getter @Setter
    private Integer inami_number;




}
