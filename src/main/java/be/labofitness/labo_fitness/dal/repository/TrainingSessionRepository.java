package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * <p>Repository interface for managing {@link TrainingSession} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 * <p>Extends {@link JpaSpecificationExecutor} to support dynamic query execution.</p>
 */
@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long>, JpaSpecificationExecutor<TrainingSession> {

    /**
     * Retrieves a {@link TrainingSession} by its ID.
     *
     * @param id The ID of the {@link TrainingSession} to retrieve.
     * @return An optional containing the {@link TrainingSession} if found, or empty otherwise.
     */
    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.id = :id")
    Optional<TrainingSession> findTrainingSessionById(Long id);

}
