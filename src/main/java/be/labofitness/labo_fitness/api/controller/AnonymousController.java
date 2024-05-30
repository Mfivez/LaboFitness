package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.service.AnonymousService;
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
     * Endpoint for retrieving all coaches.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of all available coaches.</p>
     *
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoaches() {
        return ResponseEntity.ok(anonymousService.getAllCoaches());
    }

    /**
     * Endpoint for retrieving coaches by remote availability.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of coaches based on their remote availability.</p>
     *
     * @param request the {@link GetCoachesByRemoteRequest} containing remote availability details
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-remote")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoachesByIsRemote(@Valid @ModelAttribute GetCoachesByRemoteRequest request) {
        return ResponseEntity.ok(anonymousService.getAllCoachesByIsRemote(request));
    }

    /**
     * Endpoint for retrieving coaches by name.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of coaches based on their name.</p>
     *
     * @param request the {@link GetCoachesByNameRequest} containing coach name details
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-name")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoachesByName(@Valid @ModelAttribute GetCoachesByNameRequest request) {
        return ResponseEntity.ok(anonymousService.getAllCoachesByName(request));
    }

    /**
     * Endpoint for retrieving coaches by specialization.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of coaches based on their specialization.</p>
     *
     * @param request the {@link GetCoachesBySpecializationRequest} containing coach specialization details
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-spec")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoachesBySpecialization(@Valid @ModelAttribute GetCoachesBySpecializationRequest request) {
        return ResponseEntity.ok(anonymousService.getAllCoachesBySpecialization(request));
    }

    // endregion

    //region GET PHYSIOTHERAPIST

    /**
     * Endpoint for retrieving all physiotherapists.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of all available physiotherapists.</p>
     *
     * @return a {@link ResponseEntity} containing a list of {@link GetPhysioResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/physio")
    public ResponseEntity<List<GetPhysioResponse>> getAllPhysio() {
        return ResponseEntity.ok(anonymousService.getAllPhysio());
    }

    /**
     * Endpoint for retrieving physiotherapists by name.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of physiotherapists based on their name.</p>
     *
     * @param request the {@link GetPhysioByNameRequest} containing physiotherapist name details
     * @return a {@link ResponseEntity} containing a list of {@link GetPhysioResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/physio-by-name")
    public ResponseEntity<List<GetPhysioResponse>> getAllPhysioByName(@Valid @ModelAttribute GetPhysioByNameRequest request) {
        return ResponseEntity.ok(anonymousService.getPhysioByName(request));
    }

    /**
     * Endpoint for retrieving physiotherapists by specialization.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of physiotherapists based on their specialization.</p>
     *
     * @param request the {@link GetPhysioBySpecializationRequest} containing physiotherapist specialization details
     * @return a {@link ResponseEntity} containing a list of {@link GetPhysioResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/physio-by-spec")
    public ResponseEntity<List<GetPhysioResponse>> getAllPhysioBySpecialization(@Valid @ModelAttribute GetPhysioBySpecializationRequest request) {
        return ResponseEntity.ok(anonymousService.getPhysioBySpecialization(request));
    }

    // endregion

    // region GET TRAINING SESSION

    /**
     * Endpoint for retrieving all training sessions.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of all available training sessions.</p>
     *
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/training-sessions")
    public ResponseEntity<List<GetTrainingSessionResponse>> getAllTrainingSessions() {
        return ResponseEntity.ok(anonymousService.findAllTrainingSession());
    }

    /**
     * Endpoint for retrieving training sessions by recommended level.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of training sessions based on the recommended level.</p>
     *
     * @param request the {@link GetTrainingSessionByRecommendedLvlRequest} containing recommended level details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/training-sessions-by-lvl")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByRecommendedLvl(@Valid @ModelAttribute GetTrainingSessionByRecommendedLvlRequest request) {
        return ResponseEntity.ok(anonymousService.findTrainingSessionByRecommendedLvl(request));
    }

    /**
     * Endpoint for retrieving training sessions by duration.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of training sessions based on their duration.</p>
     *
     * @param request the {@link GetTrainingSessionsByDurationRequest} containing duration details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/training-sessions-by-duration")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByDuration(@Valid @ModelAttribute GetTrainingSessionsByDurationRequest request) {
        return ResponseEntity.ok(anonymousService.findTrainingSessionByDuration(request));
    }

    /**
     * Endpoint for retrieving training sessions by name.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of training sessions based on their name.</p>
     *
     * @param request the {@link GetTrainingSessionsByNameRequest} containing training session name details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/training-sessions-by-name")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByName(@Valid @ModelAttribute GetTrainingSessionsByNameRequest request) {
        return ResponseEntity.ok(anonymousService.findTrainingSessionByName(request));
    }

    /**
     * Endpoint for retrieving training sessions by coach name.
     *
     * <p>This endpoint is accessible to {@code anonymous users} and returns a list of training sessions based on the coach's name.</p>
     *
     * @param request the {@link GetTrainingSessionsByCoachNameRequest} containing coach name details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/training-sessions-by-coach-name")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByCoachName(@Valid @ModelAttribute GetTrainingSessionsByCoachNameRequest request) {
        return ResponseEntity.ok(anonymousService.findTrainingSessionByCoachName(request));
    }

    //endregion

}
