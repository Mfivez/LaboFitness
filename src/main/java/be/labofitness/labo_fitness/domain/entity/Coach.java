package be.labofitness.labo_fitness.domain.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * Represents a {@code Coach} in the system.
 * <p>Extends the {@link Professional} class, inheriting its properties.</p>
 */
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "Coaches")
public class Coach extends Professional{

    /**
     * <p>Indicates whether the coach offers remote services.</p>
     * <p>1. {@code True}: the coach provides services remotely</p>
     * <p>2. {@code False}: services are provided in person.</p>
     */
    @Column(name = "is_remote", nullable = false)
    private boolean isRemote;

    /**
     * The price per hour for the coach's services.
     * <p>Specifies the hourly rate charged by the coach for their services.</p>
     */
    @Column(name = "price_hour", nullable = false)
    private int priceHour;

    /**
     * Default constructor initializing the remote service availability to false.
     * <p>Sets the {@code isRemote} attribute to false by default.</p>
     */
    public Coach() {
        this.isRemote = false;
    }
}
