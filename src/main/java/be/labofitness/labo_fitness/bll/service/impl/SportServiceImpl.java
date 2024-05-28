package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.bll.service.service.SportService;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepository;

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Sport} by its ID.
     *
     * @param id the ID of the {@link Sport} to retrieve
     * @return the {@link Sport} with the given ID
     */
    @Override
    public Sport getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Sport}.
     *
     * @return a list of all {@link Sport}
     */
    @Override
    public List<Sport> getAll() {
        return sportRepository.findAll();
    }

    /**
     * Creates a new {@link Sport}.
     *
     * @param entity the {@link Sport} to create
     * @return the created {@link Sport}
     */
    @Override
    public Sport create(Sport entity) {
        return null;
    }

    /**
     * Updates an existing {@link Sport}.
     *
     * @param entity the {@link Sport} to update
     * @return the updated {@link Sport}
     */
    @Override
    public Sport update(Sport entity) {
        return null;
    }

    /**
     * Deletes an {@link Sport} by its ID.
     *
     * @param id the ID of the {@link Sport} to delete
     * @return the deleted {@link Sport}, or null if not found
     */
    @Override
    public Sport delete(Long id) {
        return null;
    }

    // endregion

}
