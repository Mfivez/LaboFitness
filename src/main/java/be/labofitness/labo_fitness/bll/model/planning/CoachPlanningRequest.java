package be.labofitness.labo_fitness.bll.model.planning;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the request model for managing a coach's planning.
 * <p>
 * This record encapsulates various details such as types of activities, sports, coach name,
 * start and end dates of the planning, and client's email.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * List<String> types = List.of("Pilates", "Yoga");
 * List<String> sports = List.of("Running", "Swimming");
 * String name = "John Doe";
 * LocalDateTime startDate = LocalDateTime.now();
 * LocalDateTime endDate = startDate.plusDays(7);
 * String clientEmail = "client@example.com";
 *
 * CoachPlanningRequest request = new CoachPlanningRequest(types, sports, name, startDate, endDate, clientEmail);
 * }</pre>
 *
 * @param types        The types of activities planned by the coach.
 * @param sports       The sports planned by the coach.
 * @param name         The name of the coach.
 * @param startDate    The start date of the planning.
 * @param endDate      The end date of the planning.
 * @param clientEmail  The email address of the client assigned to the coach.
 */
public record CoachPlanningRequest(


    List<String> types,


    List<String> sports,


    String name,


    LocalDateTime startDate,


    LocalDateTime endDate,


    String clientEmail

) {
}
