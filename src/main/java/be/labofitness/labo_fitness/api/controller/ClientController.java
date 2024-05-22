package be.labofitness.labo_fitness.api.controller;


import be.labofitness.labo_fitness.bll.models.request.client.CompetitionRegister.CompetitionRegisterRequest;
import be.labofitness.labo_fitness.bll.models.request.client.TrainingSessionSubscription.TrainingSuscribRequest;
import be.labofitness.labo_fitness.bll.models.request.client.manageAccount.ClientManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.response.client.CompetitionRegister.CompetitionRegisterResponse;
import be.labofitness.labo_fitness.bll.models.response.client.TrainingSessionSubscription.TrainingSuscribResponse;
import be.labofitness.labo_fitness.bll.models.response.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    //region CLIENT MANAGE ACCOUNT

    @PostMapping("/manage-account")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<ClientManageAccountResponse> clientManageAccount(@Valid @ModelAttribute ClientManageAccountRequest request) {
        return ResponseEntity.ok(clientService.manageAccount(request));
    }

    //endregion
    
    // region GET PERSONAL TRAINING SESSION

    @GetMapping("/personal-training-sessions")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getAllPersonalTrainingSessions() {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSession());
    }

    @GetMapping("/personal-training-sessions-by-lvl")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByRecommendedLvl(@Valid @ModelAttribute GetTrainingSessionByRecommendedLvlRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByRecommendedLvl(request));
    }

    @GetMapping("/personal-training-sessions-by-duration")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByDuration(@Valid @ModelAttribute GetTrainingSessionsByDurationRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByDuration(request));
    }

    @GetMapping("/personal-training-sessions-by-name")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByName(@Valid @ModelAttribute GetTrainingSessionsByNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByName(request));
    }

    @GetMapping("/personal-training-sessions-by-coach-name")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByCoachName(@Valid @ModelAttribute GetTrainingSessionsByCoachNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByCoachName(request));
    }

    //endregion

    //region GET PERSONAL COACHES

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoaches() {
        return ResponseEntity.ok(clientService.getAllPersonalCoaches());
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches-by-remote")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesByIsRemote(@ModelAttribute GetCoachesByRemoteRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesByIsRemote(request));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches-by-name")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesByName(@Valid @ModelAttribute GetCoachesByNameRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesByName(request));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches-by-spec")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesBySpecialization(@Valid @ModelAttribute GetCoachesBySpecializationRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesBySpecialization(request));
    }

    // endregion

    //region GET PERSONAL PHYSIOTHERAPIST

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-physio")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysio() {
        return ResponseEntity.ok(clientService.getAllPersonalPhysio());
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-physio-by-name")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysioByName(@Valid @ModelAttribute GetPhysioByNameRequest request) {
        return ResponseEntity.ok(clientService.getPersonalPhysioByName(request));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-physio-by-spec")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysioBySpecialization(@Valid @ModelAttribute GetPhysioBySpecializationRequest request) {
        return ResponseEntity.ok(clientService.getPersonalPhysioBySpecialization(request));
    }

    // endregion

    // region COMPETITION REGISTER

    @PreAuthorize("isAuthenticated() AND hasAnyAuthority('CLIENT')")
    @PutMapping("/competition-register")
    public ResponseEntity<CompetitionRegisterResponse> registerToOneCompetition(@Valid @RequestBody CompetitionRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.registerToOneCompetition(request));
    }

    // endregion

    // region TRAINING SESSIONS SUBSCRIPTION

    @PreAuthorize("isAuthenticated() AND hasAnyAuthority('CLIENT')")
    @PutMapping("/training-subscription")
    public ResponseEntity<TrainingSuscribResponse> subscribeToOneTrainingSession(@Valid @RequestBody TrainingSuscribRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.subscribeToOneTrainingSession(request));
    }

    //endregion





}
