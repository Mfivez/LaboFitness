package be.labofitness.labo_fitness.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Entity
@Table(name = "Coaches")
@Getter @Setter @ToString
public class Coach extends Professional{

    @Column(name = "is_remote", nullable = false)
    private boolean isRemote;

    @Column(name = "price_hour", nullable = false)
    private int priceHour;


    public Coach() {
        this.isRemote = false;
    }
}
