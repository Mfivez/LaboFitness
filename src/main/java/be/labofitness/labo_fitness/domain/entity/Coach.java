package be.labofitness.labo_fitness.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Coaches")
public class Coach extends Professional{

    @Getter @Setter
    @Column(name = "is_remote", nullable = false)
    private boolean is_remote;

    @Getter @Setter
    @Column(name = "price_hour", nullable = false)
    private int price_hour;


}
