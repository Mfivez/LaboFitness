package be.labofitness.labo_fitness.api.controller.authentification;

import be.labofitness.labo_fitness.bll.models.request.UserLoginRequest;
import be.labofitness.labo_fitness.bll.models.request.client.registerClient.ClientRegisterRequest;
import be.labofitness.labo_fitness.bll.models.request.professionnel.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.models.response.UserLoginResponse;
import be.labofitness.labo_fitness.bll.models.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.service.ClientService;
import be.labofitness.labo_fitness.bll.service.service.ProfessionalService;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {


    private final UserService userService;
    private final ClientService clientService;
    private final ProfessionalService professionalService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@ModelAttribute UserLoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/client")
    public ResponseEntity<RegisterResponse> registerClient(@Valid @ModelAttribute ClientRegisterRequest request){
        return ResponseEntity.ok(clientService.register(request));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/professional")
    public ResponseEntity<RegisterResponse> registerProfessional(@Valid @ModelAttribute ProfessionalRegisterRequest request){
        return ResponseEntity.ok(professionalService.register(request));
    }

}