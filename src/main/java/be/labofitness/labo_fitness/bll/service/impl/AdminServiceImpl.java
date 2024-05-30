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
import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.bll.specification.AdminGetUserSpecification;
import be.labofitness.labo_fitness.bll.specification.CompetitionSpecification;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing administrative operations related to {@link User}.
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final SecurityService securityService;
    private final RoleService roleService;

    /**
     * Retrieves a list of {@link AdminGetUserResponse} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A list of {@link AdminGetUserResponse} matching the criteria.
     */
    @Override
    public List<AdminGetUserResponse> getAllUsers(AdminGetUserRequest request) {

        Specification<User> spec = Specification.where(null);

        if(request.userEmail() != null && !request.userEmail().isEmpty()){
            spec = spec.and(AdminGetUserSpecification.hasEmail(request.userEmail()));
        }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(AdminGetUserSpecification.hasName(request.name()));
        }
        if (request.id() != null) {
            spec = spec.and(AdminGetUserSpecification.hasUserId(request.id()));
        }
        if (request.isActive() != null) {
            spec = spec.and(AdminGetUserSpecification.isActive(request.isActive()));
        }

        if(request.isRemote() != null){
            spec = spec.and(AdminGetUserSpecification.isRemote(request.isRemote()));
        }

        if(request.inamiNumber() != null){
            spec = spec.and(AdminGetUserSpecification.hasInamiNumber(request.inamiNumber()));
        }
        if(request.roles() != null){
            for (String role : request.roles()) { spec = spec.and(AdminGetUserSpecification.hasRole(role)); }
        }

        return userRepository.findAll(spec).stream()
                .map(AdminGetUserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // REGION manage status account

    @Override
    public AdminManageAccountStatusResponse manageAccountStatus(AdminManageAccountStatusRequest request) {
        //TODO METH
        return AdminManageAccountStatusResponse.fromEntity(userService.updateAccountStatus(
                userService.getOneByEmail(request.email()), request.isActive()),
                "successfully"
        );
    }

    @Override
    public AdminManageEmailStatusResponse updateEmailStatus(AdminManageEmailStatusRequest request)
    {
        return AdminManageEmailStatusResponse.fromEntity(userService.updateEmailStatus(
                userService.getOneByEmail(request.email()), request.emailActive()),
                "successfully"
        );
    }

    @Override
    public AdminAnonymizeUserResponse anonymizeUser (AdminAnonymizeUserRequest request){

        return AdminAnonymizeUserResponse.fromEntity(userService.anonymizeUser(
                userService.getOneByEmail(request.email())),
                "successfully"
        );
    }

    // endregion


}
