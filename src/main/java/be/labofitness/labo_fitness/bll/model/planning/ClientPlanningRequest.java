package be.labofitness.labo_fitness.bll.model.planning;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the request model for managing a client's planning.
 * <p>
 * This record encapsulates various details such as types of activities, sports, client name,
 * start and end dates of the planning, coach's email, and physiotherapist's email.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * List<String> types = List.of("Pilates", "Yoga");
 * List<String> sports = List.of("Running", "Swimming");
 * String name = "John Doe";
 * LocalDateTime startDate = LocalDateTime.now();
 * LocalDateTime endDate = startDate.plusDays(7);
 * String coachMail = "coach@example.com";
 * String physiotherapistMail = "physio@example.com";
 *
 * ClientPlanningRequest request = new ClientPlanningRequest(types, sports, name, startDate, endDate, coachMail, physiotherapistMail);
 * }</pre>
 *
 * @param types              The types of activities planned for the client.
 * @param sports             The sports planned for the client.
 * @param name               The name of the client.
 * @param startDate          The start date of the planning.
 * @param endDate            The end date of the planning.
 * @param coachMail          The email address of the coach assigned to the client.
 * @param physiotherapistMail The email address of the physiotherapist assigned to the client.
 */
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
