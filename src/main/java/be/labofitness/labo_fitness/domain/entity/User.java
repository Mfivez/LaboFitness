package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @ToString
@AllArgsConstructor
public abstract class User extends BaseEntity<Long> implements UserDetails {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String last_name;


    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender",nullable = true)
    private Gender gender;

    @Column(name ="birthdate",nullable = false)
    private LocalDateTime birthdate;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.roles.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }



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
        this.gender = Gender.UNDEFINED;
    }

}
