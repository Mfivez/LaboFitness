package be.labofitness.labo_fitness.api.controller;

import be.labofitness.labo_fitness.bll.models.request.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.models.response.planning.PlanningResponse;
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

}
