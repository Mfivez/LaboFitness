package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.model.request.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.request.physiotherapist.manageAccount.changePassWord.PhysiotherapistChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.response.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.response.physiotherapist.manageAccount.changePassword.PhysiotherapistChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

// TODO dev a logic for the nomination's of appointments
public interface PhysiotherapistService extends CrudService<Physiotherapist, Long> {

    // PLANNING

    PlanningResponse getPlanning(PhysioPlanningRequest request) ;

    // endregion

    // region ACCOUNT MANAGEMENT

    public PhysiotherapistManageAccountResponse manageAccount(PhysiotherapistManageAccountRequest request);

    public PhysiotherapistChangePasswordResponse changePassword(PhysiotherapistChangePasswordRequest request);

    // endregion

}
