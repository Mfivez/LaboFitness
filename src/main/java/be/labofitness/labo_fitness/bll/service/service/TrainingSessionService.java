package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Service interface for managing {@link TrainingSession}.
 * <br> Extends {@link CrudService} for basic CRUD operations.
 */
public interface TrainingSessionService extends CrudService<TrainingSession, Long> {

    //region SPECIFICATION

    /**
     * Retrieves a list of {@link TrainingSession} entities based on the provided specifications.
     *
     * @param specification the specification to filter the training sessions
     * @return a list of {@link TrainingSession} entities that match the provided specifications
     */
    List<TrainingSession> findBySpecifications(Specification<TrainingSession> specification);

    //endregion

}
