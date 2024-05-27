package be.labofitness.labo_fitness.bll.model.request.planning;

import java.time.LocalDateTime;
import java.util.List;

public record CoachPlanningRequest(
    List<String> types,
    List<String> sports,
    String name,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String clientEmail
) {
}
