package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterRequest;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterResponse;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionRequest;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.*;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.changePassword.ClientChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.changePassword.ClientChangePasswordResponse;
import be.labofitness.labo_fitness.bll.model.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Endpoint for changing a client's password.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code CLIENT authority}. It handles account management requests
     * and returns a {@link ClientChangePasswordResponse} containing the details of the managed account.</p>
     *
     * @param request the {@link ClientChangePasswordRequest} containing account management details
     * @return a {@link ResponseEntity} containing the {@link ClientChangePasswordResponse}
     */
    @PutMapping("/manage-account/password")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ClientChangePasswordResponse> changePassword(@Valid @RequestBody ClientChangePasswordRequest request){
        return ResponseEntity.ok(clientService.changePassword(request));
    }

    //endregion

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
    public ResponseEntity<PlanningResponse> getPlanningWithSpecifications(@Valid @ModelAttribute ClientPlanningRequest request) {
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
    public ResponseEntity<MakeRequestForAppointmentResponse> makeARequestForAppointment(@Valid @RequestBody MakeRequestForAppointmentRequest request) {
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
    public ResponseEntity<AcceptAppointmentPlanningResponse> makeARequestForAppointment(@Valid @RequestBody AcceptAppointmentPlanningRequest request) {
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
    public ResponseEntity<CancelAppointmentResponse> makeARequestForAppointment(@Valid @RequestBody CancelAppointmentRequest request) {
        return ResponseEntity.ok(clientService.cancelAppointment(request));
    }

    // endregion

}
