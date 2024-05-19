package be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist;

import jakarta.validation.constraints.Pattern;

public record UserGetPhysioBySpecializationRequest(
        @Pattern(regexp = "^(Kine)$", message = "specialization must be 'Kine'")
        String specialization
)
{}