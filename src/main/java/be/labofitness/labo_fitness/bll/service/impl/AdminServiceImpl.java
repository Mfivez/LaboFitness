package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.model.admin.GetUsers.AdminGetUserRequest;
import be.labofitness.labo_fitness.bll.model.admin.GetUsers.AdminGetUserResponse;
import be.labofitness.labo_fitness.bll.model.admin.anonymizeUser.AdminAnonymizeUserRequest;
import be.labofitness.labo_fitness.bll.model.admin.anonymizeUser.AdminAnonymizeUserResponse;
import be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus.AdminManageAccountStatusRequest;
import be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus.AdminManageAccountStatusResponse;
import be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus.AdminManageEmailStatusRequest;
import be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus.AdminManageEmailStatusResponse;
import be.labofitness.labo_fitness.bll.service.service.AdminService;
import be.labofitness.labo_fitness.bll.service.service.SpecificationService;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import be.labofitness.labo_fitness.bll.specification.AdminGetUserSpecification;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodName;

/**
 * Service implementation for managing administrative operations related to {@link User}.
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final SpecificationService specificationService;


    /**
     * Retrieves a list of {@link AdminGetUserResponse} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A list of {@link AdminGetUserResponse} matching the criteria.
     */
    @Override
    public List<AdminGetUserResponse> getAllUsers(AdminGetUserRequest request) {

        Specification<User> spec = Specification.where(null);

        spec = specificationService.specificationHasSomething(spec, request.userEmail(), AdminGetUserSpecification::hasEmail);

        spec = specificationService.specificationHasSomething(spec, request.name(), AdminGetUserSpecification::hasName);

        spec = specificationService.specificationHasSomething(spec, request.id(), AdminGetUserSpecification::hasUserId);

        spec = specificationService.specificationHasSomething(spec, request.isActive(), AdminGetUserSpecification::isActive);

        spec = specificationService.specificationHasSomething(spec, request.isRemote(), AdminGetUserSpecification::isRemote);

        spec = specificationService.specificationHasSomething(spec, request.inamiNumber(), AdminGetUserSpecification::hasInamiNumber);

        spec = specificationService.specificationHasCollectionOfSomething(spec, request.roles(), AdminGetUserSpecification::hasRole);


        return userRepository.findAll(spec).stream()
                .map(AdminGetUserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // REGION manage status account

    /**
     * Manages the account status of a user.
     *
     * @param request the request to manage the account status
     * @return the response indicating the result of managing the account status
     */
    @Override
    public AdminManageAccountStatusResponse manageAccountStatus(AdminManageAccountStatusRequest request) {
        return AdminManageAccountStatusResponse.fromEntity(userService.updateAccountStatus(
                userService.getOneByEmail(request.email()), request.isActive()),
                getCurrentMethodName()
        );
    }

    /**
     * Updates the email status of a user.
     *
     * @param request the request to update the email status
     * @return the response indicating the result of updating the email status
     */
    @Override
    public AdminManageEmailStatusResponse updateEmailStatus(AdminManageEmailStatusRequest request)
    {
        return AdminManageEmailStatusResponse.fromEntity(userService.updateEmailStatus(
                userService.getOneByEmail(request.email()), request.emailActive()),
                getCurrentMethodName()
        );
    }

    /**
     * Anonymizes a user.
     *
     * @param request the request to anonymize the user
     * @return the response indicating the result of anonymizing the user
     */
    @Override
    public AdminAnonymizeUserResponse anonymizeUser (AdminAnonymizeUserRequest request){

        return AdminAnonymizeUserResponse.fromEntity(userService.anonymizeUser(
                userService.getOneByEmail(request.email())),
                getCurrentMethodName()
        );
    }

    // endregion


}
