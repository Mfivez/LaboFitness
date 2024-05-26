package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Coach;

public interface CoachService  extends CrudService<Coach, Long> {

    // PLANNING

    PlanningResponse getPlanning(CoachPlanningRequest request) ;

    // endregion

    // region ACCOUNT MANAGEMENT
    public CoachManageAccountResponse manageAccount(CoachManageAccountRequest request);
    // endregion
}
