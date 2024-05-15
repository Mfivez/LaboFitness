package be.labofitness.labo_fitness.domain.entity.base;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class BaseEntity <T extends Serializable> {

    @Id
    @GeneratedValue
    @Getter
    private T id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Getter @Setter
    private LocalDateTime createdAt;


    @Column(name = "updated_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Getter @Setter
    private LocalDateTime updatedAt;
}
