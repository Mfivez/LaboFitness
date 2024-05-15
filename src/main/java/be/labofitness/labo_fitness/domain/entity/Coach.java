package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Coaches")

public class Coach extends Professional{

    @Getter @Setter
    @Column(name = "is_remote", nullable = false)
    private boolean is_remote;

    @Getter @Setter
    @Column(name = "price_hour", nullable = false)
    private int price_hour;

    /**
     *  Constructeur de coach pour passer dans le constructeur de professional & de user
     * @param name de l'user coach
     * @param last_name de l'user coach
     * @param email de l'user coach
     * @param password de l'user coach
     * @param adress de l'user coach
     * @param isActive représente l'état du compte (actif, désactivé)
     * @param roles du coach ( modo admin ...)
     * @param specialization le domaine d'expertise
     * @param locationPlace le lieu de travail
     * @param price_hour le tarif du coach
     * @param is_remote donne cours en distanciel ou pas
     */
    @Builder
    public Coach(String name, String last_name, String email, String password, Adress adress, boolean isActive, Set<Role> roles,String specialization, Set<LocationPlace> locationPlace, int price_hour, boolean is_remote) {
        super(name, last_name, email, password, adress, isActive, roles, specialization, locationPlace);
        this.price_hour = price_hour;
        this.is_remote = is_remote;
    }
}
