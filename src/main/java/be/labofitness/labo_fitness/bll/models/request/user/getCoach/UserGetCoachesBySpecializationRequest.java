package be.labofitness.labo_fitness.bll.models.request.user.getCoach;

import jakarta.validation.constraints.Pattern;

public record UserGetCoachesBySpecializationRequest(
        @Pattern(regexp = "^(Yoga|Fitness)$", message = "specialization must be 'Yoga' or 'Fitness'")
        String specialization
)
{}

