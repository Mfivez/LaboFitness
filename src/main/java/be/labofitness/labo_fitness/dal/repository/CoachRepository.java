package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query(
            "SELECT c.password" +
            "FROM Coach c " +
            "WHERE c.id = :id")
    String findPasswordByCoachId(Long id);

    @Query(
            "SELECT c " +
            "FROM Coach c " +
            "WHERE c.email = :email")
    Optional<Coach> findByEmail(String email);

    @Query(
            "SELECT c " +
            "FROM Competition c " +
            "WHERE c.coach.id = :id ")
    List<Competition> findPersonalCompetitionById(Long id);

    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.coach.id = :id ")
    List<TrainingSession> findPersonalTrainingById(Long id);
}
