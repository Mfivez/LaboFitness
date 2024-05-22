package be.labofitness.labo_fitness.api.controller;

import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.models.response.user.getReport.GetReportResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // region REPORT

    //region MAKE REPORT

    @PreAuthorize("isAuthenticated() AND hasAnyAuthority('USER')")
    @PostMapping("/make-report")
    public ResponseEntity<ReportResponse> makeReport(@Valid @ModelAttribute MakeReportRequest request) {
        return ResponseEntity.ok(userService.makeReport(request));
    }

    //endregion

    //region GET REPORT
    //GetReportByIsValidateForAuthenticateUser
    @GetMapping("/get-report")
    @PreAuthorize("isAuthenticated() AND hasAnyAuthority('USER')")
    public ResponseEntity<Set<GetReportResponse>> getReportResponseByIsValidate() {
        return ResponseEntity.ok(userService.getValidReport());
    }




    // endregion

    //region GET COACHES

    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoaches() {
        return ResponseEntity.ok(userService.getAllCoaches());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-remote")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoachesByIsRemote(@ModelAttribute GetCoachesByRemoteRequest request) {
        return ResponseEntity.ok(userService.getAllCoachesByIsRemote(request));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-name")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoachesByName(@Valid @ModelAttribute GetCoachesByNameRequest request) {
        return ResponseEntity.ok(userService.getAllCoachesByName(request));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/coaches-by-spec")
    public ResponseEntity<List<GetCoachesResponse>> getAllCoachesBySpecialization(@Valid @ModelAttribute GetCoachesBySpecializationRequest request) {
        return ResponseEntity.ok(userService.getAllCoachesBySpecialization(request));
    }

    // endregion

    //region GET PHYSIOTHERAPIST

    @PreAuthorize("isAnonymous()")
    @GetMapping("/physio")
    public ResponseEntity<List<GetPhysioResponse>> getAllPhysio() {
        return ResponseEntity.ok(userService.getAllPhysio());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/physio-by-name")
    public ResponseEntity<List<GetPhysioResponse>> getAllPhysioByName(@Valid @ModelAttribute GetPhysioByNameRequest request) {
        return ResponseEntity.ok(userService.getPhysioByName(request));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/physio-by-spec")
    public ResponseEntity<List<GetPhysioResponse>> getAllPhysioBySpecialization(@Valid @ModelAttribute GetPhysioBySpecializationRequest request) {
        return ResponseEntity.ok(userService.getPhysioBySpecialization(request));
    }

    // endregion

    // region GET TRAINING SESSION

    @GetMapping("/training-sessions")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getAllTrainingSessions() {
        return ResponseEntity.ok(userService.findAllTrainingSession());
    }

    @GetMapping("/training-sessions-by-lvl")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByRecommendedLvl(@Valid @ModelAttribute GetTrainingSessionByRecommendedLvlRequest request) {
        return ResponseEntity.ok(userService.findTrainingSessionByRecommendedLvl(request));
    }

    @GetMapping("/training-sessions-by-duration")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByDuration(@Valid @ModelAttribute GetTrainingSessionsByDurationRequest request) {
        return ResponseEntity.ok(userService.findTrainingSessionByDuration(request));
    }

    @GetMapping("/training-sessions-by-name")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByName(@Valid @ModelAttribute GetTrainingSessionsByNameRequest request) {
        return ResponseEntity.ok(userService.findTrainingSessionByName(request));
    }

    @GetMapping("/training-sessions-by-coach-name")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<GetTrainingSessionResponse>> getTrainingSessionsByCoachName(@Valid @ModelAttribute GetTrainingSessionsByCoachNameRequest request) {
        return ResponseEntity.ok(userService.findTrainingSessionByCoachName(request));
    }

    //endregion
}
