package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.PhysiotherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PlanningResponse> getPlanningWithSpecifications(@ModelAttribute PhysioPlanningRequest request) {
        return ResponseEntity.ok(physiotherapistService.getPlanning(request));
    }

    // endregion

}
