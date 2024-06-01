package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.PhysiotherapistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling physiotherapist-related requests.
 * Provides an endpoint for retrieving the {@code physiotherapist's planning}.
 */
@RestController
@RequestMapping("/api/physio")
@RequiredArgsConstructor
public class PhysiotherapistController {

    private final PhysiotherapistService physiotherapistService;

    // region PLANNING

    /**
     * Endpoint for retrieving the {@code physiotherapist's planning} with specified details.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code PHYSIOTHERAPIST authority}.</p>
     * <p>It returns a planning response based on the specified details.</p>
     *
     * @param request the {@link PhysioPlanningRequest} containing planning request details
     * @return a {@link ResponseEntity} containing the {@link PlanningResponse}
     */
    @PreAuthorize("hasAuthority('PHYSIOTHERAPIST')")
    @GetMapping("/Planning")
    public ResponseEntity<PlanningResponse> getPlanningWithSpecifications(@Valid @ModelAttribute PhysioPlanningRequest request) {
        return ResponseEntity.ok(physiotherapistService.getPlanning(request));
    }

    // endregion

    //region PHYSIOTHERAPIST MANAGE ACCOUNT

    /**
     * Endpoint for managing a coach's account.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code COACH authority}.</p>
     * <p>It handles account management requests and returns a {@link PhysiotherapistManageAccountResponse} containing the details of the managed account.</p>
     *
     * @param request the {@link PhysiotherapistManageAccountRequest} containing account management details
     * @return a {@link ResponseEntity} containing the {@link PhysiotherapistManageAccountResponse}
     */
    @PutMapping("/manage-account")
    @PreAuthorize("hasAuthority('PHYSIOTHERAPIST') and isAuthenticated()")
    public ResponseEntity<PhysiotherapistManageAccountResponse> physiotherapistManageAccount(@Valid @RequestBody PhysiotherapistManageAccountRequest request){
        return ResponseEntity.ok(physiotherapistService.manageAccount(request));
    }

    //endregion

}

