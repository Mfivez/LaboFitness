package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    //BUG GITHUB
    // region PERSONAL TRAINING SESSION

    @Query(
            "SELECT c.trainingSessions " +
            "FROM Client c " +
            "WHERE c.id = :clientId")
    List<TrainingSession> findPersonalTrainingSessionsByClientId(Long clientId);

    @Query(
            "SELECT t " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE c.id = :clientId " +
            "AND t.recommended_level = :recommendedLevel")
    List<TrainingSession> findPersonalTrainingSessionsByRecommendedLevel(Long clientId, RecommendedLevel recommendedLevel);


    @Query(
            "SELECT t " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE c.id = :clientId " +
            "AND t.duration = :duration")
    List<TrainingSession> findPersonalTrainingSessionsByDuration(Long clientId, int duration);

    @Query(
            "SELECT t " +
                    "FROM Client c " +
                    "JOIN c.trainingSessions t " +
                    "WHERE c.id = :clientId " +
                    "AND t.name = :name")
    List<TrainingSession> findPersonalTrainingSessionsByName(Long clientId, String name);

    @Query(
            "SELECT t " +
                    "FROM Client c " +
                    "JOIN c.trainingSessions t " +
                    "WHERE c.id = :clientId " +
                    "AND t.coach.name = :coachName")
    List<TrainingSession> findPersonalTrainingSessionsByCoachName(Long clientId, String coachName);

    // endregion

    // region TRAINING SESSION

    @Query(
            "SELECT t " +
            "FROM TrainingSession t ")
    List<TrainingSession> findAllTrainingSessions();

    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.recommended_level = :recommendedLevel ")
    List<TrainingSession> findTrainingSessionsByRecommendedLevel(RecommendedLevel recommendedLevel);

    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.duration = :duration")
    List<TrainingSession> findTrainingSessionsByDuration(int duration);

    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.name = :name")
    List<TrainingSession> findTrainingSessionsByName(String name);

    @Query(
            "SELECT t " +
                    "FROM TrainingSession t " +
                    "WHERE t.coach.name = :coachName")
    List<TrainingSession> findTrainingSessionsByCoachName(String coachName);


    // endregion
}
