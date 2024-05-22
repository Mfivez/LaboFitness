package be.labofitness.labo_fitness.api.controller;


import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.service.CoachService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {


    private final CoachService coachService;

    @PostMapping("/manage-account")
    @PreAuthorize("hasAuthority('COACH') and isAuthenticated()")
    public ResponseEntity<CoachManageAccountResponse> clientManageAccount(@Valid @ModelAttribute CoachManageAccountRequest request) {
        return ResponseEntity.ok(coachService.manageAccount(request));
    }
}
