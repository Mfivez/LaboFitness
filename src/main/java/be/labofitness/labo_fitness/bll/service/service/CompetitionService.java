package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Competition;
import org.springframework.stereotype.Service;

@Service
public interface CompetitionService  extends CrudService<Competition, Long>  {

    // region UTILS METHODS

    Competition getCompetitionByCompetitionNameId(String name);


    // endregion

}
