package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.base.Address;

/**
 * Service interface for managing {@link LocationPlace}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface LocationService extends CrudService<LocationPlace, Long> {

    LocationPlace addLocationPlace(Address address);

    LocationPlace updateLocationPlace(LocationPlace locationPlace);
}
