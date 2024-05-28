package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionResponse;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.model.request.coach.manageAccount.changePassword.CoachChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.response.coach.manageAccount.changePassword.CoachChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Coach;

public interface CoachService  extends CrudService<Coach, Long> {

    // PLANNING

    PlanningResponse getPlanning(CoachPlanningRequest request) ;

    // endregion

    // region ACCOUNT MANAGEMENT

    CoachManageAccountResponse manageAccount(CoachManageAccountRequest request);

    CoachChangePasswordResponse changePassword(CoachChangePasswordRequest request);

    // endregion

    // region EVENT INSCRIPTION MANAGEMENT

    ManageEventInscriptionResponse manageCompetitionInscription(ManageEventInscriptionRequest request);

    ManageEventInscriptionResponse manageTrainingInscription(ManageEventInscriptionRequest request);

    // endregion
}
