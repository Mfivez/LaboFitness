package be.labofitness.labo_fitness.bll.model.planning;


import java.time.LocalDateTime;
import java.util.List;

public record PlanningResponse(
        List<LocalDateTime> startDates,
        List<LocalDateTime> endDates,
        List<String> eventNames
) {
}
