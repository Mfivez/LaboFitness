package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.admin.GetUsers.AdminGetUserRequest;
import be.labofitness.labo_fitness.bll.model.admin.GetUsers.AdminGetUserResponse;
import be.labofitness.labo_fitness.bll.model.admin.anonymizeUser.AdminAnonymizeUserRequest;
import be.labofitness.labo_fitness.bll.model.admin.anonymizeUser.AdminAnonymizeUserResponse;
import be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus.AdminManageAccountStatusRequest;
import be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus.AdminManageAccountStatusResponse;
import be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus.AdminManageEmailStatusRequest;
import be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus.AdminManageEmailStatusResponse;
import be.labofitness.labo_fitness.bll.service.service.AdminService;
import be.labofitness.labo_fitness.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling administrative operations.
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * Retrieves a list of {@link User} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link AdminGetUserResponse} matching the criteria.
     */
    @GetMapping("/get-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AdminGetUserResponse>> getAllUsers(@Valid @ModelAttribute AdminGetUserRequest request){
        return ResponseEntity.ok(adminService.getAllUsers(request));
    }

    /**
     * Manage the email status of a {@link User} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link AdminManageEmailStatusResponse} matching the criteria.
     */
    @PutMapping("/manage-account/email-status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AdminManageEmailStatusResponse> updateEmailStatus(@Valid @RequestBody AdminManageEmailStatusRequest request){
        return ResponseEntity.ok(adminService.updateEmailStatus(request));
    }

    /**
     * Manage the account status of a {@link User} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link AdminManageAccountStatusResponse} matching the criteria.
     */
    @PutMapping("/manage-account/account-status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AdminManageAccountStatusResponse> manageAccountStatus(@Valid @RequestBody AdminManageAccountStatusRequest request){
        return ResponseEntity.ok(adminService.manageAccountStatus(request));
    }

    /**
     * Logical delete of an {@link User} account based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link AdminManageAccountStatusResponse} matching the criteria.
     */
    @PutMapping("/manage-user/anonymize")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AdminAnonymizeUserResponse> anonymizeUser (@Valid @RequestBody AdminAnonymizeUserRequest request){
        return ResponseEntity.ok(adminService.anonymizeUser(request));
    }




    
}
