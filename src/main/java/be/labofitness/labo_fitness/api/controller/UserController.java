package be.labofitness.labo_fitness.api.controller;

import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.UserGetCoachesResponse;
import be.labofitness.labo_fitness.bll.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    //region GET COACHES
    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches")
    public ResponseEntity<List<UserGetCoachesResponse>> getAllCoaches() {
        return ResponseEntity.ok(userService.getAllCoaches());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-remote")
    public ResponseEntity<List<UserGetCoachesResponse>> getAllCoachesByIsRemote(@ModelAttribute UserGetCoachesByRemoteRequest request) {
        return ResponseEntity.ok(userService.getAllCoachesByIsRemote(request));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-name")
    public ResponseEntity<List<UserGetCoachesResponse>> getAllCoachesByName(@Valid @ModelAttribute UserGetCoachesByNameRequest request) {
        return ResponseEntity.ok(userService.getAllCoachesByName(request));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-spec")
    public ResponseEntity<List<UserGetCoachesResponse>> getAllCoachesBySpecialization(@Valid @ModelAttribute UserGetCoachesBySpecializationRequest request) {
        return ResponseEntity.ok(userService.getAllCoachesBySpecialization(request));
    }
    // endregion


}
