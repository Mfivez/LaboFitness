package be.labofitness.labo_fitness.api.controller;

import be.labofitness.labo_fitness.bll.model.request.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.response.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.PhysiotherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/physio")
@RequiredArgsConstructor
public class PhysiotherapistController {

    private final PhysiotherapistService physiotherapistService;

    // region PLANNING
    //TODO REFAC ISAUTHORIZE BCZ USELESS (ON EVERY CONTROLLER OF PROG)
    @PreAuthorize("hasAuthority('PHYSIOTHERAPIST')")
    @GetMapping("/Planning")
    public ResponseEntity<PlanningResponse> getPlanningWithSpecifications(@ModelAttribute PhysioPlanningRequest request) {
        return ResponseEntity.ok(physiotherapistService.getPlanning(request));
    }

    // endregion

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

