package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.service.service.CompetitionService;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.domain.entity.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of the {@link CompetitionService} interface.
 * Provides CRUD operations for managing {@link Competition} entities.
 */
@RequiredArgsConstructor
@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    // region UTILS METHODS

    /**
     * Retrieves a {@link Competition} by its name ID.
     *
     * @param name the name ID of the {@link Competition} to retrieve
     * @return the {@link Competition} with the given name ID
     * @throws RuntimeException if the competition is not found
     */
    @Override
    public Competition getCompetitionByCompetitionNameId(String name) {
        return competitionRepository.findByCompetitionNameId(name).orElseThrow(() -> new RuntimeException("Competition not found"));}

    // endregion

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Competition} by its ID.
     *
     * @param id the ID of the {@link Competition} to retrieve
     * @return the {@link Competition} with the given ID
     */
    @Override
    public Competition getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Competition}.
     *
     * @return a list of all {@link Competition}
     */
    @Override
    public List<Competition> getAll() {
        return competitionRepository.findAll();
    }

    /**
     * Creates a new {@link Competition}.
     *
     * @param entity the {@link Competition} to create
     * @return the created {@link Competition}
     */
    @Override
    public Competition create(Competition entity) {
        return null;
    }

    /**
     * Updates an existing {@link Competition}.
     *
     * @param entity the {@link Competition} to update
     * @return the updated {@link Competition}
     */
    @Override
    public Competition update(Competition entity) {
        return null;
    }

    /**
     * Deletes an {@link Competition} by its ID.
     *
     * @param id the ID of the {@link Competition} to delete
     * @return the deleted {@link Competition}, or null if not found
     */
    @Override
    public Competition delete(Long id) {
        return null;
    }

    // endregion

}
