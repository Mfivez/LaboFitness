package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.LocationRepository;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Data initializer for populating the database with initial {@link LocationPlace} data.
 */
@Component
@RequiredArgsConstructor
@Order(3)
public class LocationPlaceDataIni extends DataInitializer {

    private final LocationRepository locationPlaceRepository;

    /**
     * Populates the database with initial {@link LocationPlace} data if no {@link LocationPlace} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (locationPlaceRepository.count() == 0) {
            Address address1 = new Address("123 Street", "B", "City", "12345");
            LocationPlace locationPlace1 = new LocationPlace(address1);

            Address address2 = new Address("456 Avenue", "B",  "Town", "67890");
            LocationPlace locationPlace2 = new LocationPlace(address2);

            locationPlaceRepository.save(locationPlace1);
            locationPlaceRepository.save(locationPlace2);
        }
    }
}
