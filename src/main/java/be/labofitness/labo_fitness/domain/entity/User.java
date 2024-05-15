package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<Long> {

    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Getter @Setter
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Getter @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "user_city")),
            @AttributeOverride(name = "number", column = @Column(name = "user_number")),
            @AttributeOverride(name = "street", column = @Column(name = "user_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "user_zipcode"))
    })
    private Adress adress;

    @Getter @Setter
    @Column(name = "is_active")
    private boolean isActive;


    //TODO COMPRENDRE ET VERIFIER CE QU'IL SE PASSE AU NIVEAU DU DATAINILIALIZER ET DES TABLES AVEC FAISAL
    @ManyToMany
    private Set<Role> roles;

    // ************************* CONSTRUCTOR TEST DATA INI ************************* //
    public User(String name, String last_name, String email, String password, Adress adress, boolean isActive) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.isActive = isActive;
    }


}
