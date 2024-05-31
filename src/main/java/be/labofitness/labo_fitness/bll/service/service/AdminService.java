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
public interface AdminService {

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

    /**
     * Manages the account status of a user based on the provided request.
     *
     * @param request the request to manage account status
     * @return the response indicating the result of the account status management
     */
    AdminManageAccountStatusResponse manageAccountStatus(AdminManageAccountStatusRequest request);

    /**
     * Updates the email status of a user based on the provided request.
     *
     * @param request the request to update email status
     * @return the response indicating the result of the email status update
     */
    AdminManageEmailStatusResponse updateEmailStatus(AdminManageEmailStatusRequest request);

    /**
     * Anonymizes a user based on the provided request.
     *
     * @param request the request to anonymize user
     * @return the response indicating the result of the user anonymization
     */
    AdminAnonymizeUserResponse anonymizeUser(AdminAnonymizeUserRequest request);

    // endregion
}