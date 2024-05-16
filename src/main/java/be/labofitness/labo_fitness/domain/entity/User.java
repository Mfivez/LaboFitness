package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @ToString
@AllArgsConstructor
public abstract class User extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String last_name;


    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;



    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "user_city")),
            @AttributeOverride(name = "number", column = @Column(name = "user_number")),
            @AttributeOverride(name = "street", column = @Column(name = "user_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "user_zipcode"))
    })
    private Adress adress;


    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_mail_active")
    private boolean isMailActive;


    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;



    //**** CONSTRUCTOR ****
    public User() {
        this.isActive = true;
        this.isMailActive = false;
        this.roles = new HashSet<>();
    }

}
