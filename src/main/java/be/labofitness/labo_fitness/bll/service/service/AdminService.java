package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.admin.GetUsers.AdminGetUserRequest;
import be.labofitness.labo_fitness.bll.model.admin.GetUsers.AdminGetUserResponse;
import be.labofitness.labo_fitness.bll.model.admin.anonymizeUser.AdminAnonymizeUserRequest;
import be.labofitness.labo_fitness.bll.model.admin.anonymizeUser.AdminAnonymizeUserResponse;
import be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus.AdminManageAccountStatusRequest;
import be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus.AdminManageAccountStatusResponse;
import be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus.AdminManageEmailStatusRequest;
import be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus.AdminManageEmailStatusResponse;
import be.labofitness.labo_fitness.domain.entity.User;

import java.util.List;

/**
 * Service interface for performing administrative operations.
 */
public interface AdminService
{

    //region GET USER

    /**
     * Retrieves a list of {@link User} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A list of {@link User} responses matching the criteria.
     */
    List<AdminGetUserResponse> getAllUsers(AdminGetUserRequest request);

    // endregion

    //region MANAGE ACCOUNT USER

    AdminManageAccountStatusResponse manageAccountStatus(AdminManageAccountStatusRequest request);

    AdminManageEmailStatusResponse updateEmailStatus(AdminManageEmailStatusRequest request);

    AdminAnonymizeUserResponse anonymizeUser (AdminAnonymizeUserRequest request);

    // endregion
}
