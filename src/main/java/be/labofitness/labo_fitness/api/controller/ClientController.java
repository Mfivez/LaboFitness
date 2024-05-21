package be.labofitness.labo_fitness.api.controller;

import be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession.*;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.response.client.getTrainingSession.ClientGetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // region GET PERSONAL TRAINING SESSION

    @GetMapping("/personal-training-sessions")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getAllPersonalTrainingSessions(@ModelAttribute ClientGetPersonalTrainingSessionByClientIdRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByClientId(request));
    }

    @GetMapping("/personal-training-sessions-by-lvl")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getPersonalTrainingSessionsByRecommendedLvl(@ModelAttribute ClientGetPersonalTrainingSessionByRecommendedLvlRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByRecommendedLvl(request));
    }

    @GetMapping("/personal-training-sessions-by-duration")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getPersonalTrainingSessionsByDuration(@ModelAttribute ClientGetPersonalTrainingSessionsByDurationRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByDuration(request));
    }

    @GetMapping("/personal-training-sessions-by-name")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getPersonalTrainingSessionsByName(@ModelAttribute ClientGetPersonalTrainingSessionsByNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByName(request));
    }

    @GetMapping("/personal-training-sessions-by-coach-name")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getPersonalTrainingSessionsByCoachName(@ModelAttribute ClientGetPersonalTrainingSessionsByCoachNameRequest request) {
        return ResponseEntity.ok(clientService.findPersonalClientTrainingSessionByCoachName(request));
    }

    //endregion

    // region GET PERSONAL TRAINING SESSION

    @GetMapping("/training-sessions")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getAllTrainingSessions() {
        return ResponseEntity.ok(clientService.findAllTrainingSession());
    }

    @GetMapping("/training-sessions-by-lvl")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getTrainingSessionsByRecommendedLvl(@ModelAttribute ClientGetTrainingSessionByRecommendedLvlRequest request) {
        return ResponseEntity.ok(clientService.findTrainingSessionByRecommendedLvl(request));
    }

    @GetMapping("/training-sessions-by-duration")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getTrainingSessionsByDuration(@ModelAttribute ClientGetTrainingSessionsByDurationRequest request) {
        return ResponseEntity.ok(clientService.findTrainingSessionByDuration(request));
    }

    @GetMapping("/training-sessions-by-name")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getTrainingSessionsByName(@ModelAttribute ClientGetTrainingSessionsByNameRequest request) {
        return ResponseEntity.ok(clientService.findTrainingSessionByName(request));
    }

    @GetMapping("/training-sessions-by-coach-name")
    @Secured("CLIENT")
    public ResponseEntity<List<ClientGetTrainingSessionResponse>> getTrainingSessionsByCoachName(@ModelAttribute ClientGetTrainingSessionsByCoachNameRequest request) {
        return ResponseEntity.ok(clientService.findTrainingSessionByCoachName(request));
    }

    //endregion





}
