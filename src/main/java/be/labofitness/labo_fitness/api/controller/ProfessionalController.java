package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalAddLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalUpdateLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalAddLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalUpdateLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.service.service.ProfessionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professional")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService professionalService;


    // region LOCATION PLACE

    @PostMapping("/add-location-place")
    @PreAuthorize("hasAnyAuthority('PHYSIOTHERAPIST', 'COACH')")
    public ResponseEntity<ProfessionalAddLocationPlaceResponse> addLocationPlace(@RequestBody ProfessionalAddLocationPlaceRequest request) {
        return ResponseEntity.ok(professionalService.addLocationPlace(request));
    }

    @PutMapping("/update-location-place")
    @PreAuthorize("hasAnyAuthority('PHYSIOTHERAPIST', 'COACH')")
    public ResponseEntity<ProfessionalUpdateLocationPlaceResponse> updateLocationPlace(@RequestBody ProfessionalUpdateLocationPlaceRequest request) {
        return ResponseEntity.ok(professionalService.updateLocationPlace(request));
    }
}
