package be.labofitness.labo_fitness.bll.model.request.planning;

import java.time.LocalDateTime;

public record PhysioPlanningRequest(
    String name,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String clientEmail
) {
}
