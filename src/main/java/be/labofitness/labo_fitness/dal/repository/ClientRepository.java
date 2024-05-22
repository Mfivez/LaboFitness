package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // region PERSONAL TRAINING SESSION

    @Query(
            "SELECT c.trainingSessions " +
            "FROM Client c " +
            "WHERE c.id = :clientId")
    List<TrainingSession> findPersonalTrainingSessions(Long clientId);

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

    //region PERSONAL COACH
    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE c.id = :clientId")
    List<Coach> findAllPersonalCoaches(Long clientId);

    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE t.coach.isRemote = :is_remote " +
            "AND c.id = :clientId")
    List<Coach> findPersonalCoachesByIsRemote(Long clientId, boolean is_remote);

    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE t.coach.name = :coach_name " +
            "AND c.id = :clientId")
    List<Coach> findPersonalCoachesByName(Long clientId, String coach_name);

    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE t.coach.specialization = :specialization " +
            "AND c.id = :clientId")
    List<Coach> findPersonalCoachesBySpecialization(Long clientId, String specialization);
    // endregion

    //region PERSONAL PHYSIOTHERAPIST

    @Query(
            "SELECT a.physiotherapist " +
            "FROM Appointment a " +
            "WHERE a.client.id = :clientId")
    List<Physiotherapist> findAllPersonalPhysio(Long clientId);

    @Query(
            "SELECT a.physiotherapist " +
            "FROM Appointment a " +
            "WHERE a.client.id = :clientId " +
            "AND a.physiotherapist.name = :physiotherapist_name")
    List<Physiotherapist> findPersonalPhysioByName(Long clientId, String physiotherapist_name);

    @Query(
            "SELECT a.physiotherapist " +
                    "FROM Appointment a " +
                    "WHERE a.client.id = :clientId " +
                    "AND a.physiotherapist.specialization = :specialization")
    List<Physiotherapist> findPersonalPhysioBySpecialization(Long clientId, String specialization);

    //endregion

}
