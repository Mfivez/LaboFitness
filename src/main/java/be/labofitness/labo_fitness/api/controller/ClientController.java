package be.labofitness.labo_fitness.api.controller;


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
import be.labofitness.labo_fitness.bll.models.response.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ClientManageAccountResponse> clientManageAccount(Authentication authentication, @Valid @ModelAttribute ClientManageAccountRequest request) {
        return ResponseEntity.ok(clientService.manageAccount(authentication, request));
    }

    //endregion
    
    // region GET PERSONAL TRAINING SESSION

    @GetMapping("/personal-training-sessions")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getAllPersonalTrainingSessions(Authentication authentication) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSession(authentication));
    }

    @GetMapping("/personal-training-sessions-by-lvl")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByRecommendedLvl(Authentication authentication, @Valid @ModelAttribute GetTrainingSessionByRecommendedLvlRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByRecommendedLvl(authentication, request));
    }

    @GetMapping("/personal-training-sessions-by-duration")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByDuration(Authentication authentication, @Valid @ModelAttribute GetTrainingSessionsByDurationRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByDuration(authentication,request));
    }

    @GetMapping("/personal-training-sessions-by-name")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByName(Authentication authentication, @Valid @ModelAttribute GetTrainingSessionsByNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByName(authentication, request));
    }

    @GetMapping("/personal-training-sessions-by-coach-name")
    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getPersonalTrainingSessionsByCoachName(Authentication authentication, @Valid @ModelAttribute GetTrainingSessionsByCoachNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByCoachName(authentication, request));
    }

    //endregion

    //region GET PERSONAL COACHES

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoaches(Authentication authentication) {
        return ResponseEntity.ok(clientService.getAllPersonalCoaches(authentication));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches-by-remote")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesByIsRemote(Authentication authentication, @ModelAttribute GetCoachesByRemoteRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesByIsRemote(authentication, request));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches-by-name")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesByName(Authentication authentication, @Valid @ModelAttribute GetCoachesByNameRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesByName(authentication, request));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-coaches-by-spec")
    public ResponseEntity<List<GetCoachesResponse>> getAllPersonalCoachesBySpecialization(Authentication authentication, @Valid @ModelAttribute GetCoachesBySpecializationRequest request) {
        return ResponseEntity.ok(clientService.getAllPersonalCoachesBySpecialization(authentication, request));
    }

    // endregion

    //region GET PERSONAL PHYSIOTHERAPIST

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-physio")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysio(Authentication authentication) {
        return ResponseEntity.ok(clientService.getAllPersonalPhysio(authentication));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-physio-by-name")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysioByName(Authentication authentication, @Valid @ModelAttribute GetPhysioByNameRequest request) {
        return ResponseEntity.ok(clientService.getPersonalPhysioByName(authentication, request));
    }

    @PreAuthorize("hasAuthority('CLIENT') and isAuthenticated()")
    @GetMapping("/personal-physio-by-spec")
    public ResponseEntity<List<GetPhysioResponse>> getAllPersonalPhysioBySpecialization(Authentication authentication, @Valid @ModelAttribute GetPhysioBySpecializationRequest request) {
        return ResponseEntity.ok(clientService.getPersonalPhysioBySpecialization(authentication, request));
    }

    // endregion



}
