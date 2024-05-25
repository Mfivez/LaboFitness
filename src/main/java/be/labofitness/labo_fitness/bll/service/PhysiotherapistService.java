package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.physiotherapist.manageAccount.changePassWord.PhysiotherapistChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.response.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.physiotherapist.manageAccount.changePassword.PhysiotherapistChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysiotherapistService  extends CrudService<Physiotherapist, Long> {


    // region ACCOUNT MANAGEMENT

    public PhysiotherapistManageAccountResponse manageAccount(PhysiotherapistManageAccountRequest request);

    public PhysiotherapistChangePasswordResponse changePassword(PhysiotherapistChangePasswordRequest request);

    // endregion
}
