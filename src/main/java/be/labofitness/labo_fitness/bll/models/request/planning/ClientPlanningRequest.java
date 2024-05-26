package be.labofitness.labo_fitness.bll.models.request.planning;

import java.time.LocalDateTime;
import java.util.List;

public record ClientPlanningRequest(
    List<String> types,
    List<String> sports,
    String name,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String coachMail,
    String physiotherapistMail
) {
}