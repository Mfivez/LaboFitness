package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;

/**
 * Service interface for managing {@link TrainingSession}.
 * <br> Extends {@link CrudService} for basic CRUD operations.
 */
public interface TrainingSessionService extends CrudService<TrainingSession, Long> {
}
