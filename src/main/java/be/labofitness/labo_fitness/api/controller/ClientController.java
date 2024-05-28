package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterRequest;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionRequest;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.AcceptAppointmentPlanningRequest;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.CancelAppointmentRequest;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.MakeRequestForAppointmentRequest;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterResponse;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.AcceptAppointmentPlanningResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.CancelAppointmentResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.MakeRequestForAppointmentResponse;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling client-related requests.
 * Provides endpoints for :
 * <p>1. {@code managing client accounts}</p>
 * <p>2. {@code retrieving personal training sessions and coaches}</p>
 * <p>3. {@code registering for competitions}</p>
 * <p>4. {@code subscribing to training sessions}</p>
 * <p>5. {@code managing planning}</p>
 * <p>6. {@code making appointments with physiotherapists}</p>
 */
@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    //region CLIENT MANAGE ACCOUNT

    /**
     * Endpoint for managing a client's account.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}. It handles account management requests
     * and returns a {@link ClientManageAccountResponse} containing the details of the managed account.</p>
     *
     * @param request the {@link ClientManageAccountRequest} containing account management details
     * @return a {@link ResponseEntity} containing the {@link ClientManageAccountResponse}
     */
    @PutMapping("/manage-account")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ClientManageAccountResponse> clientManageAccount(@Valid @RequestBody ClientManageAccountRequest request) {
        return ResponseEntity.ok(clientService.manageAccount(request));
    }

    @PutMapping("/manage-account/password")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ClientChangePasswordResponse> changePassword(@Valid @RequestBody ClientChangePasswordRequest request){
        return ResponseEntity.ok(clientService.changePassword(request));
    }

    //endregion
    
    // region GET PERSONAL TRAINING SESSION

    /**
     * Endpoint for retrieving all personal training sessions.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}. It returns a list of personal training sessions.</p>
     *
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-training-sessions")
    public ResponseEntity<List<GetTrainingSessionResponse>> getAllPersonalTrainingSessions() {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSession());
    }

    /**
     * Endpoint for retrieving personal training sessions by recommended level.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of training sessions filtered by the recommended level.</p>
     *
     * @param request the {@link GetTrainingSessionByRecommendedLvlRequest} containing the recommended level details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-training-sessions-by-lvl")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByRecommendedLvl(@Valid @ModelAttribute GetTrainingSessionByRecommendedLvlRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByRecommendedLvl(request));
    }

    /**
     * Endpoint for retrieving personal training sessions by duration.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of training sessions filtered by duration.</p>
     *
     * @param request the {@link GetTrainingSessionsByDurationRequest} containing the duration details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-training-sessions-by-duration")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByDuration(@Valid @ModelAttribute GetTrainingSessionsByDurationRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByDuration(request));
    }

    /**
     * Endpoint for retrieving personal training sessions by name.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of training sessions filtered by name.</p>
     *
     * @param request the {@link GetTrainingSessionsByNameRequest} containing the name details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-training-sessions-by-name")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByName(@Valid @ModelAttribute GetTrainingSessionsByNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByName(request));
    }

    /**
     * Endpoint for retrieving personal training sessions by coach name.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of training sessions filtered by coach name.</p>
     *
     * @param request the {@link GetTrainingSessionsByCoachNameRequest} containing the coach name details
     * @return a {@link ResponseEntity} containing a list of {@link GetTrainingSessionResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-training-sessions-by-coach-name")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByCoachName(@Valid @ModelAttribute GetTrainingSessionsByCoachNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByCoachName(request));
    }

    //endregion

    //region GET PERSONAL COACHES

    /**
     * Endpoint for retrieving all personal coaches.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of personal coaches.</p>
     *
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-coaches")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoaches() {
        return ResponseEntity.ok(clientService.getAllPersonalCoaches());
    }

    /**
     * Endpoint for retrieving all personal coaches by remote availability.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of personal coaches filtered by remote availability.</p>
     *
     * @param request the {@link GetCoachesByRemoteRequest} containing the remote availability details
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-coaches-by-remote")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesByIsRemote(@ModelAttribute GetCoachesByRemoteRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesByIsRemote(request));
    }

    /**
     * Endpoint for retrieving all personal coaches by name.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of personal coaches filtered by name.</p>
     *
     * @param request the {@link GetCoachesByNameRequest} containing the name details
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-coaches-by-name")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesByName(@Valid @ModelAttribute GetCoachesByNameRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesByName(request));
    }

    /**
     * Endpoint for retrieving all personal coaches by specialization.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p>It returns a list of personal coaches filtered by specialization.</p>
     *
     * @param request the {@link GetCoachesBySpecializationRequest} containing the specialization details
     * @return a {@link ResponseEntity} containing a list of {@link GetCoachesResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-coaches-by-spec")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesBySpecialization(@Valid @ModelAttribute GetCoachesBySpecializationRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesBySpecialization(request));
    }

    // endregion

    //region GET PERSONAL PHYSIOTHERAPIST

    /**
     * Endpoint for retrieving all personal physiotherapists.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It returns a list of personal physiotherapists.</p>
     *
     * @return a {@link ResponseEntity} containing a list of {@link GetPhysioResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-physio")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysio() {
        return ResponseEntity.ok(clientService.getAllPersonalPhysio());
    }

    /**
     * Endpoint for retrieving all personal physiotherapists by name.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It returns a list of personal physiotherapists filtered by name.</p>
     *
     * @param request the {@link GetPhysioByNameRequest} containing the name details
     * @return a {@link ResponseEntity} containing a list of {@link GetPhysioResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-physio-by-name")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysioByName(@Valid @ModelAttribute GetPhysioByNameRequest request) {
        return ResponseEntity.ok(clientService.getPersonalPhysioByName(request));
    }

    /**
     * Endpoint for retrieving all personal physiotherapists by specialization.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It returns a list of personal physiotherapists filtered by specialization.</p>
     *
     * @param request the {@link GetPhysioBySpecializationRequest} containing the specialization details
     * @return a {@link ResponseEntity} containing a list of {@link GetPhysioResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/personal-physio-by-spec")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysioBySpecialization(@Valid @ModelAttribute GetPhysioBySpecializationRequest request) {
        return ResponseEntity.ok(clientService.getPersonalPhysioBySpecialization(request));
    }

    // endregion

    // region COMPETITION REGISTER

    /**
     * Endpoint for registering to a competition.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It handles competition registration requests and returns a {@link CompetitionRegisterResponse} containing the registration details.</p>
     *
     * @param request the {@link CompetitionRegisterRequest} containing competition registration details
     * @return a {@link ResponseEntity} containing the {@link CompetitionRegisterResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @PutMapping("/competition-register")
    public ResponseEntity<CompetitionRegisterResponse> registerToOneCompetition(@Valid @RequestBody CompetitionRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.registerToOneCompetition(request));
    }

    // endregion

    // region TRAINING SESSIONS SUBSCRIPTION

    /**
     * Endpoint for subscribing to a training session.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It handles training session subscription requests and returns a {@link TrainingSubscriptionResponse} containing the subscription details.</p>
     *
     * @param request the {@link TrainingSubscriptionRequest} containing training session subscription details
     * @return a {@link ResponseEntity} containing the {@link TrainingSubscriptionResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @PutMapping("/training-subscription")
    public ResponseEntity<TrainingSubscriptionResponse> subscribeToOneTrainingSession(@Valid @RequestBody TrainingSubscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.subscribeToOneTrainingSession(request));
    }

    //endregion

    // region PLANNING

    /**
     * Endpoint for retrieving the client's planning with specifications.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It returns a planning response with specified details.</p>
     *
     * @param request the {@link ClientPlanningRequest} containing planning request details
     * @return a {@link ResponseEntity} containing the {@link PlanningResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/Planning")
    public ResponseEntity<PlanningResponse> getPlanningWithSpecifications(@ModelAttribute ClientPlanningRequest request) {
        return ResponseEntity.ok(clientService.getPlanning(request));
    }

    // endregion

    // region APPOINTMENT WITH PHYSIO

    /**
     * Endpoint for making a request for an appointment with a physiotherapist.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It handles appointment requests and returns a {@link MakeRequestForAppointmentResponse} containing the appointment details.</p>
     *
     * @param request the {@link MakeRequestForAppointmentRequest} containing appointment request details
     * @return a {@link ResponseEntity} containing the {@link MakeRequestForAppointmentResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/make-request-appointment")
    public ResponseEntity<MakeRequestForAppointmentResponse> makeARequestForAppointment(@RequestBody MakeRequestForAppointmentRequest request) {
        return ResponseEntity.ok(clientService.makeRequestForAppointment(request));
    }

    /**
     * Endpoint for accepting an appointment planning.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It handles appointment acceptance requests and returns an  {@link AcceptAppointmentPlanningResponse} containing the accepted appointment details.</p>
     *
     * @param request the {@link AcceptAppointmentPlanningRequest} containing appointment acceptance details
     * @return a {@link ResponseEntity} containing the {@link AcceptAppointmentPlanningResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @PutMapping("/accept-appointment-planning")
    public ResponseEntity<AcceptAppointmentPlanningResponse> makeARequestForAppointment(@RequestBody AcceptAppointmentPlanningRequest request) {
        return ResponseEntity.ok(clientService.acceptAppointmentPlanning(request));
    }

    /**
     * Endpoint for canceling an appointment.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}.</p>
     * <p> It handles appointment cancellation requests and returns a {@link CancelAppointmentResponse} containing the cancellation details.</p>
     *
     * @param request the {@link CancelAppointmentRequest} containing appointment cancellation details
     * @return a {@link ResponseEntity} containing the {@link CancelAppointmentResponse}
     */
    @PreAuthorize("hasAuthority('CLIENT')")
    @PutMapping("/cancel-appointment")
    public ResponseEntity<CancelAppointmentResponse> makeARequestForAppointment(@RequestBody CancelAppointmentRequest request) {
        return ResponseEntity.ok(clientService.cancelAppointment(request));
    }

    // endregion

}
