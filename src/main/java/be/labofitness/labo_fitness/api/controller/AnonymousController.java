package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.user.getCoach.*;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecificationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.*;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.service.service.AnonymousService;
import be.labofitness.labo_fitness.domain.entity.Coach;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller for handling anonymous-related requests.
 * Provides endpoints for :
 * <p>1. {@code retrieving coaches}</p>
 * <p>2. {@code retrieving physiotherapists}</p>
 * <p>3. {@code retrieving training sessions}</p>
 */
@RestController
@RequestMapping("/api/anonymous")
@RequiredArgsConstructor
public class AnonymousController {

    private final AnonymousService anonymousService;

    //region GET COACHES

    /**
     * Retrieves a list of {@link Coach} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link GetCoachesResponse} matching the criteria.
     */
    @PreAuthorize("isAnonymous() OR hasAuthority('CLIENT')")
    @GetMapping("/coaches")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoachesBySpecification(@Valid @ModelAttribute GetCoachBySpecificationRequest request) {
        return ResponseEntity.ok(anonymousService.getAllCoachesBySpecification(request));
    }

    // endregion

    //region GET PHYSIOTHERAPIST

    /**
     * Retrieves a list of {@link Coach} based on the provided request criteria.
     *
     * @param request The request containing filtering criteria.
     * @return A ResponseEntity containing a list of {@link GetCoachesResponse} matching the criteria.
     */
    @PreAuthorize("isAnonymous() OR hasAuthority('CLIENT')")
    @GetMapping("/physio")
    public ResponseEntity<List<GetPhysioResponse>> getAllPhysioBySpecification(@Valid @ModelAttribute GetPhysioBySpecificationRequest request) {
        return ResponseEntity.ok(anonymousService.getAllPhysioBySpecification(request));
    }

    // endregion

    // region GET TRAINING SESSION

    @PreAuthorize("isAnonymous() OR hasAuthority('CLIENT')")
    @GetMapping("/training-sessions")
    public ResponseEntity<List<GetTrainingSessionResponse>> getAllTrainingSessionsBySpecification(@Valid @ModelAttribute GetTrainingSessionBySpecificationRequest request) {
        return ResponseEntity.ok(anonymousService.getAllTrainingSessionsBySpecification(request));
    }

    //endregion

}
