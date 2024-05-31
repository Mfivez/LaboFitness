package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Competition;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

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

    // region SPECIFICATION

    /**
     * Retrieves a list of {@link Competition} entities based on the provided specifications.
     *
     * @param specification the specification to filter the competitions
     * @return a list of {@link Competition} entities that match the provided specifications
     */
    List<Competition> getCompetitionBySpecification(Specification<Competition> specification);

    //endregion

}
