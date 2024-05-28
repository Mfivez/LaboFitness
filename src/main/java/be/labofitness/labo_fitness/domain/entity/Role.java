package be.labofitness.labo_fitness.domain.entity;
import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a {@code role} entity in the system.
 * <br>Roles are used to grant specific permissions to users.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Entity
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
@Table (name = "roles")
public class Role extends BaseEntity<Long> implements GrantedAuthority {

    /**
     * The name of the {@code role}.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * The description of the {@code role}.
     */
    @Column(name = "description")
    private String description;

    /**
     * Constructs a {@code role} with the given description.
     * <br>The name is set to {@code "USER"} by default.
     * @param description The description of the {@code role}.
     */
    public Role(String description) {
        this.name = "USER";
        this.description = description;
    }

    /**
     * Returns the authority of the {@code role}, which is its name.
     * @return The name of the {@code role}.
     */
    @Override
    public String getAuthority() {
        return name;
    }
}