package be.labofitness.labo_fitness.api.controller;

import be.labofitness.labo_fitness.bll.models.request.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.physiotherapist.manageAccount.changePassWord.PhysiotherapistChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.response.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.physiotherapist.manageAccount.changePassword.PhysiotherapistChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.PhysiotherapistService;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/physiotherapist")
public class PhysiotherapistController {

    private final PhysiotherapistService physiotherapistService;


    //region PHYSIOTHERAPIST MANAGE ACCOUNT

    @PutMapping("/manage-account")
    @PreAuthorize("hasAuthority('PHYSIOTHERAPIST') and isAuthenticated()")
    public ResponseEntity<PhysiotherapistManageAccountResponse> physiotherapistManageAccount(@Valid @RequestBody PhysiotherapistManageAccountRequest request){
        return ResponseEntity.ok(physiotherapistService.manageAccount(request));
    }

    @PutMapping("/manage-account/password")
    @PreAuthorize("hasAuthority('PHYSIOTHERAPIST') and isAuthenticated()")
    public ResponseEntity<PhysiotherapistChangePasswordResponse> changePassword(@Valid @RequestBody PhysiotherapistChangePasswordRequest request){
        return ResponseEntity.ok(physiotherapistService.changePassword(request));

    }

    //endregion
}
