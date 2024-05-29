package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionResponse;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.changePassword.CoachChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.changePassword.CoachChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;

/**
 * Service interface for managing {@link Coach}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface CoachService  extends CrudService<Coach, Long> {

    // region PLANNING

    /**
     * Retrieves the planning for a {@link Coach}.
     *
     * @param request the {@link Coach} planning request
     * @return the planning response
     */
    PlanningResponse getPlanning(CoachPlanningRequest request) ;

    // endregion

    // region ACCOUNT MANAGEMENT

    /**
     * Manages the account of a {@link Coach}.
     *
     * @param request the {@link Coach} manage account request
     * @return the {@link Coach} manage account response
     */
    CoachManageAccountResponse manageAccount(CoachManageAccountRequest request);

    /**
     * Changes the password of a {@link Coach}.
     *
     * @param request the {@link Coach} change password request
     * @return the {@link Coach} change password response
     */
    CoachChangePasswordResponse changePassword(CoachChangePasswordRequest request);

    // endregion

    // region EVENT INSCRIPTION MANAGEMENT

    /**
     * Manages event inscription for a {@link Competition}.
     *
     * @param request the manage event inscription request
     * @return the manage event inscription response
     */
    ManageEventInscriptionResponse manageCompetitionInscription(ManageEventInscriptionRequest request);

    /**
     * Manages event inscription for a {@link TrainingSession}.
     *
     * @param request the manage event inscription request
     * @return the manage event inscription response
     */
    ManageEventInscriptionResponse manageTrainingInscription(ManageEventInscriptionRequest request);

    // endregion

    // region CLASSIC CRUD

    Coach getOneByEmail(String email);

    //endregion

}
