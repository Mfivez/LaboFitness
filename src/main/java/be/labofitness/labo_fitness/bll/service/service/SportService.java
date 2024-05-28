package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Sport;

/**
 * Service interface for managing {@link Sport}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface SportService extends CrudService<Sport, Long> {
}
