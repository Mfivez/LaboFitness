package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.addAccredition.ProfessionalAddAccreditationRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.addAccredition.ProfessionalAddAccreditationResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.updateAccreditation.ProfessionalUpdateAccreditationRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.updateAccreditation.ProfessionalUpdateAccreditationResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.addLocationPlace.ProfessionalAddLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.updateLocationPlace.ProfessionalUpdateLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.addLocationPlace.ProfessionalAddLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.updateLocationPlace.ProfessionalUpdateLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.service.service.ProfessionalService;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professional")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService professionalService;

    // region MANAGE ACCOUNT

    /**
     * add to a {@link Professional} account an {@link LocationPlace } based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link ProfessionalAddLocationPlaceResponse} matching the criteria.
     */
    @PostMapping("/add-location-place")
    @PreAuthorize("hasAnyAuthority('PHYSIOTHERAPIST', 'COACH')")
    public ResponseEntity<ProfessionalAddLocationPlaceResponse> addLocationPlace(@RequestBody ProfessionalAddLocationPlaceRequest request) {
        return ResponseEntity.ok(professionalService.addLocationPlace(request));
    }

    /**
     * update to a {@link Professional} account the {@link LocationPlace } based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link ProfessionalUpdateLocationPlaceResponse} matching the criteria.
     */
    @PutMapping("/update-location-place")
    @PreAuthorize("hasAnyAuthority('PHYSIOTHERAPIST', 'COACH')")
    public ResponseEntity<ProfessionalUpdateLocationPlaceResponse> updateLocationPlace(@RequestBody ProfessionalUpdateLocationPlaceRequest request) {
        return ResponseEntity.ok(professionalService.updateLocationPlace(request));
    }


    /**
     * add to a {@link Professional} account an {@link Accreditation } based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link ProfessionalAddAccreditationResponse} matching the criteria.
     */
    @PostMapping("/manage-account/add-accredition")
    @PreAuthorize("hasAnyAuthority('PHYSIOTHERAPIST', 'COACH')")
    public ResponseEntity<ProfessionalAddAccreditationResponse> addAccreditation(@RequestBody ProfessionalAddAccreditationRequest request){
        return ResponseEntity.ok(professionalService.addAccreditation(request));
    }

    /**
     * update from an {@link Professional} an {@link Accreditation } based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link ProfessionalAddAccreditationResponse} matching the criteria.
     */
    @PutMapping("/manage-account/update-accredidation")
    @PreAuthorize("hasAnyAuthority('PHYSIOTHERAPIST', 'COACH')")
    public ResponseEntity<ProfessionalUpdateAccreditationResponse> updateAccreditation(@RequestBody ProfessionalUpdateAccreditationRequest request){
        return ResponseEntity.ok(professionalService.updateAccreditation(request));
    }

    // endregion
}
