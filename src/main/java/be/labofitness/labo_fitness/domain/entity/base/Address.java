package be.labofitness.labo_fitness.domain.entity.base;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>Represents an {@code address entity} used as an {@code embedded type} within other entities. </p>
 *
 * <p>An {@code address} typically consists of
 * a {@code street}, a {@code number}, a {@code city} and a {@code zip code}.</p>
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    /**
     * The street name.
     */
    private String street;

    /**
     * The street number.
     */
    private String number;

    /**
     * The city.
     */
    private String city;

    /**
     * The postal code or ZIP code.
     */
    private String zipcode;
}