package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;


@Entity
@AllArgsConstructor
@Table (name = "roles")
@Getter @Setter @ToString
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role extends BaseEntity<Long> implements GrantedAuthority {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    public Role(String description) {
        this.name = "USER";
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}