package be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession;

import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetTrainingSessionResponse {
    private String name;
    private int duration;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;
    private RecommendedLevel recommended_level;
    private String coach_name;
    private String coach_lastname;
    private String coach_mail;

    public GetTrainingSessionResponse(String name, int duration, LocalDateTime start_date, LocalDateTime end_date, String description, RecommendedLevel recommended_level, String coach_name, String coach_lastname, String coach_mail) {
        this.name = name;
        this.duration = duration;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.recommended_level = recommended_level;
        this.coach_name = coach_name;
        this.coach_lastname = coach_lastname;
        this.coach_mail = coach_mail;
    }

    public static GetTrainingSessionResponse fromEntity(TrainingSession trainingSession) {
        return new GetTrainingSessionResponse(
                trainingSession.getName(),
                trainingSession.getDuration(),
                trainingSession.getStart_date(),
                trainingSession.getEnd_date(),
                trainingSession.getDescription(),
                trainingSession.getRecommended_level(),
                trainingSession.getCoach().getName(),
                trainingSession.getCoach().getLast_name(),
                trainingSession.getCoach().getEmail()
        );
    }
}
