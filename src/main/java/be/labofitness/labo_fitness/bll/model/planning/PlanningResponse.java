package be.labofitness.labo_fitness.bll.model.planning;
import java.time.LocalDateTime;

/**
 * Represents the response model for managing planning data.
 * <p>
 * This record encapsulates lists of start dates, end dates, and event names for planning.
 * </p>

 * PlanningResponse response = new PlanningResponse(startDates, endDates, eventNames);
 * }</pre>
 *
 * @param startDates  The list of start dates for events in the planning.
 * @param endDates    The list of end dates for events in the planning.
 * @param eventNames  The list of names corresponding to the events in the planning.
 */
public record PlanningResponse(
        LocalDateTime startDates,
        LocalDateTime endDates,
        String eventNames
) {

    public static PlanningResponse fromEntity(LocalDateTime startDates, LocalDateTime endDates, String eventNames){
        return new PlanningResponse(
                startDates,
                endDates,
                eventNames
        );
    }

}
