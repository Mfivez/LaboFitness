package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {

    @Query(
            "SELECT t " +
                    "FROM TrainingSession t " +
                    "WHERE t.id = :id")
    Optional<TrainingSession> findTrainingSessionById(Long id);


}
