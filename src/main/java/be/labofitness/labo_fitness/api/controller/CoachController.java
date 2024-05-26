package be.labofitness.labo_fitness.api.controller;


import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.planning.PlanningResponse;
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

    // region PLANNING

    @GetMapping("/planning")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<PlanningResponse> getPlanning(@ModelAttribute CoachPlanningRequest request) {
        return ResponseEntity.ok(coachService.getPlanning(request));
    }

    // endregion

    // region MANAGE ACCOUNT
    @PostMapping("/manage-account")
    @PreAuthorize("hasAuthority('COACH') and isAuthenticated()")
    public ResponseEntity<CoachManageAccountResponse> coachManageAccount(@Valid @ModelAttribute CoachManageAccountRequest request) {
        return ResponseEntity.ok(coachService.manageAccount(request));
    }
    // endregion
}
