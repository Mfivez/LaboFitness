package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.dal.util.HasGetIdMethod;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import be.labofitness.labo_fitness.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a {@code user} entity in the system.
 * <p>{@code Users} can have different {@code roles and permissions}, and this class provides
 * common attributes and methods for all types of {@code users}.</p>
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User extends BaseEntity<Long> implements UserDetails, HasGetIdMethod {

    /**
     * The name of the {@code user}.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The last name of the {@code user}.
     */
    @Column(name = "last_name", nullable = false)
    private String lastname;

    /**
     * The email address of the {@code user}.
     * <br>This is unique for each user.
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * The password of the {@code user}.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The {@link Gender} of the {@code user}.
     */
    @Column(name = "gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * The birthdate of the {@code user}.
     */
    @Column(name ="birthdate",nullable = false)
    private LocalDateTime birthdate;

    /**
     * The {@link Address} of the {@code user}.
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "user_city")),
            @AttributeOverride(name = "number", column = @Column(name = "user_number")),
            @AttributeOverride(name = "street", column = @Column(name = "user_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "user_zipcode"))
    })
    private Address address;

    /**
     * Indicates whether the {@code user} account is active.
     */
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * Indicates whether email notifications for the {@code user} are active.
     */
    @Column(name = "is_mail_active")
    private boolean isMailActive;

    /**
     * The {@link Role} associated with the {@code user}.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /**
     * Retrieves the {@link GrantedAuthority} to the {@code user}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Roles: " + roles);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Indicates whether the {@code user} account has expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the {@code user} account is locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether {@code user} user credentials have expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the {@code user} account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return isActive;
    }

    /**
     * Retrieves the username of the {@code user}, which is the email address.
     */
    @Override
    public String getUsername() {
        return getEmail();
    }

    /**
     * <p>Constructs a {@code user} with default values : </p>
     * <p>1. The account is set to active by default</p>
     * <p>2. email notifications are disabled by default.</p>
     */
    public User() {
        this.isActive = true;
        this.isMailActive = false;
        this.roles = new HashSet<>();
        this.gender = Gender.UNDEFINED;
    }

}
