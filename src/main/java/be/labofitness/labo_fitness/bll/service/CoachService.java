package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.client.manageAccount.ClientManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.client.manageAccount.changePassword.ClientChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.changePassword.CoachChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.response.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.changePassword.CoachChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

public interface CoachService  extends CrudService<Coach, Long> {

    // region ACCOUNT MANAGEMENT

    public CoachManageAccountResponse manageAccount(CoachManageAccountRequest request);

    public CoachChangePasswordResponse changePassword(CoachChangePasswordRequest request);
    // endregion
}
