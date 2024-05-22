package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@Service
public interface CompetitionService  extends CrudService<Competition, Long>  {

    // region UTILS METHODS

    Competition getCompetitionByCompetitionNameId(String name);


    // endregion

}
