package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.base.Address;

/**
 * Service interface for managing {@link LocationPlace}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface LocationService extends CrudService<LocationPlace, Long> {

    /**
     * Adds a new location place with the provided address.
     *
     * @param address the address details for the new location place
     * @return the newly added location place
     */
    LocationPlace addLocationPlace(Address address);

    /**
     * Updates an existing location place.
     *
     * @param locationPlace the updated location place entity
     * @return the updated location place entity
     */
    LocationPlace updateLocationPlace(LocationPlace locationPlace);

}
