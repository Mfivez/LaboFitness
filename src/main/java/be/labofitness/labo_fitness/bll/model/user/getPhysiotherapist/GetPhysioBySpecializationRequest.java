package be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist;

import jakarta.validation.constraints.Pattern;

public record GetPhysioBySpecializationRequest(
        @Pattern(regexp = "^(Kine)$", message = "specialization must be 'Kine'")
        String specialization
)
{}