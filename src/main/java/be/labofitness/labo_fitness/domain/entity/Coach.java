package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@Entity
@Table(name = "Coaches")
@Getter @Setter @ToString
public class Coach extends Professional{

    @Column(name = "is_remote", nullable = false)
    private boolean is_remote;

    @Column(name = "price_hour", nullable = false)
    private int price_hour;


    public Coach() {
        this.is_remote = false;
    }

//    @Builder //absolument pas nécessaire
//    public Coach(String name, String lastName, String email, String password, Adress adress, Set<Role> roles, String specialization, boolean is_remote, int price_hour) {
//        super(name, lastName, email, password, adress, roles, specialization);
//        this.is_remote = is_remote;
//        this.price_hour = price_hour;
//    }
}
