package be.labofitness.labo_fitness.api.controller;


import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.changePassword.CoachChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.changePassword.CoachChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.CoachService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {


    private final CoachService coachService;


    //region COACH MANAGE ACCOUNT
    @PutMapping("/manage-account")
    @PreAuthorize("hasAuthority('COACH') and isAuthenticated()")
    public ResponseEntity<CoachManageAccountResponse> coachManageAccount(@Valid @RequestBody CoachManageAccountRequest request) {
        return ResponseEntity.ok(coachService.manageAccount(request));
    }

    @PutMapping("/manage-account/password")
    @PreAuthorize("hasAuthority('COACH') and isAuthenticated()")
    public ResponseEntity<CoachChangePasswordResponse> changePassword(@Valid @RequestBody CoachChangePasswordRequest request){
        return ResponseEntity.ok(coachService.changePassword(request));
    }

    //endregion
}
