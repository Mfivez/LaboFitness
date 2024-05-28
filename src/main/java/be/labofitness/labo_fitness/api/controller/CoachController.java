package be.labofitness.labo_fitness.api.controller;


import be.labofitness.labo_fitness.bll.model.request.coach.ManageEventInscription.ManageEventInscriptionRequest;
import be.labofitness.labo_fitness.bll.model.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.request.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.response.coach.ManageEventInscription.ManageEventInscriptionResponse;
import be.labofitness.labo_fitness.bll.model.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.response.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.CoachService;
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
    // region PLANNING

    @GetMapping("/planning")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<PlanningResponse> getPlanning(@ModelAttribute CoachPlanningRequest request) {
        return ResponseEntity.ok(coachService.getPlanning(request));
    }

    // endregion

    // region MANAGE ACCOUNT
    @PostMapping("/manage-account")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<CoachManageAccountResponse> coachManageAccount(@Valid @RequestBody CoachManageAccountRequest request) {
        return ResponseEntity.ok(coachService.manageAccount(request));
    }

    @PutMapping("/manage-account/password")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<CoachChangePasswordResponse> changePassword(@Valid @RequestBody CoachChangePasswordRequest request){
        return ResponseEntity.ok(coachService.changePassword(request));
    }

    //endregion

    //region OPEN EVENT INSCRIPTION

    @PostMapping("/competition/inscription")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<ManageEventInscriptionResponse> manageCompetitionInscription(@Valid @RequestBody ManageEventInscriptionRequest request) {
        return ResponseEntity.ok(coachService.manageCompetitionInscription(request));
    }

    @PostMapping("/training/inscription")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<ManageEventInscriptionResponse> manageTrainingInscription(@Valid @RequestBody ManageEventInscriptionRequest request) {
        return ResponseEntity.ok(coachService.manageTrainingInscription(request));
    }

    //endregion

}
