package be.labofitness.labo_fitness.bll.model.planning;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the response model for managing planning data.
 * <p>
 * This record encapsulates lists of start dates, end dates, and event names for planning.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * List<LocalDateTime> startDates = List.of(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
 * List<LocalDateTime> endDates = List.of(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusDays(1).plusHours(1));
 * List<String> eventNames = List.of("Event 1", "Event 2");
 *
 * PlanningResponse response = new PlanningResponse(startDates, endDates, eventNames);
 * }</pre>
 *
 * @param startDates  The list of start dates for events in the planning.
 * @param endDates    The list of end dates for events in the planning.
 * @param eventNames  The list of names corresponding to the events in the planning.
 */
public record PlanningResponse(
        List<LocalDateTime> startDates,
        List<LocalDateTime> endDates,
        List<String> eventNames
) {
}
