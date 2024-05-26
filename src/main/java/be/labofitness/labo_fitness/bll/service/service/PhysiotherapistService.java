package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.models.request.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.models.response.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

// TODO dev a logic for the nomination's of appointments
public interface PhysiotherapistService extends CrudService<Physiotherapist, Long> {

    // PLANNING

    PlanningResponse getPlanning(PhysioPlanningRequest request) ;

    // endregion

}
