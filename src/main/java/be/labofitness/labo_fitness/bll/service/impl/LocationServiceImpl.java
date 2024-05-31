package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.service.service.LocationService;
import be.labofitness.labo_fitness.dal.repository.LocationRepository;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of the {@link LocationService} interface providing operations to manage location places.
 */
@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link LocationPlace} by its ID.
     *
     * @param id the ID of the {@link LocationPlace} to retrieve
     * @return the {@link LocationPlace} with the given ID
     */
    @Override
    public LocationPlace getOne(Long id) {
        return locationRepository.findById(id).orElseThrow(
                () -> new DoesntExistException("location with id " + id + " does not exist")
        );
    }

    /**
     * Retrieves all {@link LocationPlace}.
     *
     * @return a list of all {@link LocationPlace}
     */
    @Override
    public List<LocationPlace> getAll() {
        return locationRepository.findAll();
    }

    /**
     * Creates a new {@link LocationPlace}.
     *
     * @param entity the {@link LocationPlace} to create
     * @return the created {@link LocationPlace}
     */
    @Override
    public LocationPlace create(LocationPlace entity) {
        return null;
    }

    /**
     * Updates an existing {@link LocationPlace}.
     *
     * @param entity the {@link LocationPlace} to update
     * @return the updated {@link LocationPlace}
     */
    @Override
    public LocationPlace update(LocationPlace entity) {
        return locationRepository.save(entity);
    }

    /**
     * Deletes an {@link LocationPlace} by its ID.
     *
     * @param id the ID of the {@link LocationPlace} to delete
     * @return the deleted {@link LocationPlace}, or null if not found
     */
    @Override
    public LocationPlace delete(Long id) {
        return null;
    }

    // endregion

    /**
     * Adds a new location place with the provided address.
     *
     * @param address the address of the location place to add
     * @return the created location place
     */
    @Override
    public LocationPlace addLocationPlace(Address address) {
        LocationPlace locationPlace = new LocationPlace(address);
        return locationRepository.save(locationPlace);
    }

    /**
     * Updates an existing location place.
     *
     * @param locationPlace the location place to update
     * @return the updated location place
     */
    @Override
    public LocationPlace updateLocationPlace(LocationPlace locationPlace) {
        return locationRepository.save(locationPlace);
    }

}
