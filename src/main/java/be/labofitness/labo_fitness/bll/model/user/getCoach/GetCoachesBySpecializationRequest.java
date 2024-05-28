package be.labofitness.labo_fitness.bll.model.user.getCoach;

import jakarta.validation.constraints.Pattern;

public record GetCoachesBySpecializationRequest(
        @Pattern(regexp = "^(Yoga|Fitness)$", message = "specialization must be 'Yoga' or 'Fitness'")
        String specialization
)
{}

