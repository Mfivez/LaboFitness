package be.labofitness.labo_fitness.api.controller.authentification;
import be.labofitness.labo_fitness.bll.model.login.UserLoginRequest;
import be.labofitness.labo_fitness.bll.model.register.ClientRegisterRequest;
import be.labofitness.labo_fitness.bll.model.register.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.model.login.UserLoginResponse;
import be.labofitness.labo_fitness.bll.model.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.service.ClientService;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import be.labofitness.labo_fitness.bll.service.service.ProfessionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Controller for handling authentication-related requests.</p>
 * Provides endpoints for {@code user login} and {@code registration} for both {@code clients} and {@code professionals}.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;
    private final ClientService clientService;
    private final ProfessionalService professionalService;

    /**
     * Endpoint for user login. <p>{@code User}</p>
     *                          <p>{@code Client}</p>
     *                          <p>{@code Physiotherapist}</p>
     *                          <p>{@code Coach}</p>
     *
     * <p>This endpoint is accessible to {@code anonymous users}. It handles user login requests and
     * returns a {@link UserLoginResponse} containing authentication details.</p>
     *
     * @param request the {@link UserLoginRequest} containing login credentials
     * @return a {@link ResponseEntity} containing the {@link UserLoginResponse}
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @ModelAttribute UserLoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    /**
     * Endpoint for {@code client registration}.
     *
     * <p>This endpoint is accessible to {@code anonymous users}. It handles registration requests for clients
     * and returns a {@link RegisterResponse} containing registration details.</p>
     *
     * @param request the {@link ClientRegisterRequest} containing client registration details
     * @return a {@link ResponseEntity} containing the {@link RegisterResponse}
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/client")
    public ResponseEntity<RegisterResponse> registerClient(@Valid @ModelAttribute ClientRegisterRequest request){
        return ResponseEntity.ok(clientService.register(request));
    }

    /**
     * Endpoint for {@code professional registration}.
     *
     * <p>This endpoint is accessible to {@code anonymous users}. It handles registration requests for {@code professionals}
     * and returns a {@link RegisterResponse} containing registration details.</p>
     *
     * @param request the {@link ProfessionalRegisterRequest} containing professional registration details
     * @return a {@link ResponseEntity} containing the {@link RegisterResponse}
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/professional")
    public ResponseEntity<RegisterResponse> registerProfessional(@Valid @ModelAttribute ProfessionalRegisterRequest request){
        return ResponseEntity.ok(professionalService.register(request));
    }

}