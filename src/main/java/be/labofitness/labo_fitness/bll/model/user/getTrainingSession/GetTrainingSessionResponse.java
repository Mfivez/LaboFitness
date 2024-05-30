package be.labofitness.labo_fitness.bll.model.user.getTrainingSession;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Represents the response model for retrieving training sessions.
 * <p>
 * This class encapsulates various details such as name, start date, end date, description,
 * recommended level, coach name, coach last name, and coach email of training sessions.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * TrainingSession trainingSession = new TrainingSession();
 * GetTrainingSessionResponse response = GetTrainingSessionResponse.fromEntity(trainingSession);
 * }</pre>
 */
@Data
public class GetTrainingSessionResponse {
    private String name;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;
    private RecommendedLevel recommended_level;
    private String coach_name;
    private String coach_lastname;
    private String coach_mail;

    public GetTrainingSessionResponse(String name, LocalDateTime start_date, LocalDateTime end_date, String description, RecommendedLevel recommended_level, String coach_name, String coach_lastname, String coach_mail) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.recommended_level = recommended_level;
        this.coach_name = coach_name;
        this.coach_lastname = coach_lastname;
        this.coach_mail = coach_mail;
    }

    /**
     * Constructs a {@code GetTrainingSessionResponse} object from a {@link TrainingSession} entity.
     *
     * @param trainingSession The training session entity.
     * @return A new {@code GetTrainingSessionResponse} object.
     */
    public static GetTrainingSessionResponse fromEntity(TrainingSession trainingSession) {
        return new GetTrainingSessionResponse(
                trainingSession.getName(),
                trainingSession.getStartDate(),
                trainingSession.getEndDate(),
                trainingSession.getDescription(),
                trainingSession.getRecommendedLevel(),
                trainingSession.getCoach().getName(),
                trainingSession.getCoach().getLastname(),
                trainingSession.getCoach().getEmail()
        );
    }
}
