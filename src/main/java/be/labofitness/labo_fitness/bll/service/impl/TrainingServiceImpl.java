package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.service.service.TrainingSessionService;
import be.labofitness.labo_fitness.dal.repository.TrainingSessionRepository;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of the {@link TrainingSessionService} interface.
 * <br>Provides methods to manage {@link TrainingSessionService}.
 */
@RequiredArgsConstructor
@Service
public class TrainingServiceImpl implements TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link TrainingSession} by its ID.
     *
     * @param id the ID of the {@link TrainingSession} to retrieve
     * @return the {@link TrainingSession} with the given ID
     */
    @Override
    public TrainingSession getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link TrainingSession}.
     *
     * @return a list of all {@link TrainingSession}
     */
    @Override
    public List<TrainingSession> getAll() {
        return trainingSessionRepository.findAll();
    }

    /**
     * Creates a new {@link TrainingSession}.
     *
     * @param entity the {@link TrainingSession} to create
     * @return the created {@link TrainingSession}
     */
    @Override
    public TrainingSession create(TrainingSession entity) {
        return null;
    }

    /**
     * Updates an existing {@link TrainingSession}.
     *
     * @param entity the {@link TrainingSession} to update
     * @return the updated {@link TrainingSession}
     */
    @Override
    public TrainingSession update(TrainingSession entity) {
        return null;
    }

    /**
     * Deletes an {@link TrainingSession} by its ID.
     *
     * @param id the ID of the {@link TrainingSession} to delete
     * @return the deleted {@link TrainingSession}, or null if not found
     */
    @Override
    public TrainingSession delete(Long id) {
        return null;
    }

    // endregion

}
