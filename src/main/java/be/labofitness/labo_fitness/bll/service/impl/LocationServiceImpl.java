package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.service.service.LocationService;
import be.labofitness.labo_fitness.dal.repository.LocationRepository;
import be.labofitness.labo_fitness.domain.entity.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import java.util.List;

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
        return null;
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
        return null;
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

}
