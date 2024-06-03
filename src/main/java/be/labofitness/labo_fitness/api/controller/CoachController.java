package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionRequest;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionResponse;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations.CoachUpdateSpecificsInformationsRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations.CoachUpdateSpecificsInformationsResponse;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.CoachService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling coach-related requests.
 * Provides endpoints for
 * <p>1. {@code managing coach accounts}</p>
 * <p>2. {@code retrieving planning}</p>
 * <p>3. {@code managing event inscriptions}</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {

    private final CoachService coachService;

    // region PLANNING

    /**
     * Endpoint for retrieving the coach's planning.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code COACH authority}.</p>
     * <p>It returns a planning response with specified details.</p>
     *
     * @param request the {@link CoachPlanningRequest} containing planning request details
     * @return a {@link ResponseEntity} containing the {@link PlanningResponse}
     */
    @PreAuthorize("hasAuthority('COACH')")
    @GetMapping("/planning")
    public ResponseEntity<List<PlanningResponse>> getPlanning(@Valid @ModelAttribute CoachPlanningRequest request) {
        return ResponseEntity.ok(coachService.getPlanning(request));
    }

    // endregion

    // region MANAGE ACCOUNT

    /**
     * Endpoint for managing a coach's account.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code COACH authority}.</p>
     * <p>It handles account management requests and returns a {@link CoachManageAccountResponse} containing the details of the managed account.</p>
     *
     * @param request the {@link CoachManageAccountRequest} containing account management details
     * @return a {@link ResponseEntity} containing the {@link CoachManageAccountResponse}
     */
    @PutMapping("/manage-account")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<CoachManageAccountResponse> coachManageAccount(@Valid @RequestBody CoachManageAccountRequest request) {
        return ResponseEntity.ok(coachService.manageAccount(request));
    }

    /**
     * Endpoint for changing a specifics information.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}. It handles account management requests
     * and returns a {@link CoachUpdateSpecificsInformationsResponse} containing the details of the managed account.</p>
     *
     * @param request the {@link CoachUpdateSpecificsInformationsRequest} containing account management details
     * @return a {@link ResponseEntity} containing the {@link CoachUpdateSpecificsInformationsResponse}
     */
    @PutMapping("/manage-account/updateSpecifics")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<CoachUpdateSpecificsInformationsResponse> updateSpecificInformations(@Valid @RequestBody CoachUpdateSpecificsInformationsRequest request){
        return ResponseEntity.ok(coachService.updateSpecificInformations(request));
    }

    // endregion

    //region OPEN EVENT INSCRIPTION

    /**
     * Endpoint for managing competition inscription.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code COACH authority}.</p>
     * <p>It handles competition inscription requests and returns a {@link ManageEventInscriptionResponse} containing the inscription details.</p>
     *
     * @param request the {@link ManageEventInscriptionRequest} containing competition inscription details
     * @return a {@link ResponseEntity} containing the {@link ManageEventInscriptionResponse}
     */
    @PreAuthorize("hasAuthority('COACH')")
    @PostMapping("/competition/inscription")
    public ResponseEntity<ManageEventInscriptionResponse> manageCompetitionInscription(@Valid @RequestBody ManageEventInscriptionRequest request) {
        return ResponseEntity.ok(coachService.manageCompetitionInscription(request));
    }

    /**
     * Endpoint for managing training inscription.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code COACH authority}.</p>
     * <p>It handles training inscription requests and returns a {@link ManageEventInscriptionResponse} containing the inscription details.</p>
     *
     * @param request the {@link ManageEventInscriptionRequest} containing training inscription details
     * @return a {@link ResponseEntity} containing the {@link ManageEventInscriptionResponse}
     */
    @PreAuthorize("hasAuthority('COACH')")
    @PostMapping("/training/inscription")
    public ResponseEntity<ManageEventInscriptionResponse> manageTrainingInscription(@Valid @RequestBody ManageEventInscriptionRequest request) {
        return ResponseEntity.ok(coachService.manageTrainingInscription(request));
    }

    //endregion

}
