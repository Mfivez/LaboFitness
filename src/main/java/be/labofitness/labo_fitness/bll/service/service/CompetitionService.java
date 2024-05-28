package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing {@link Competition}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface CompetitionService  extends CrudService<Competition, Long>  {

    // region UTILS METHODS

    /**
     * Retrieves a {@link Competition} by its name.
     *
     * @param name the name of the {@link Competition}
     * @return the {@link Competition} with the given name
     */
    Competition getCompetitionByCompetitionNameId(String name);

    // endregion

}
