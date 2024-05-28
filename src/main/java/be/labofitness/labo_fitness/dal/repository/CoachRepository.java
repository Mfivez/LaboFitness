package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * <p>Repository interface for managing {@link Coach} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 */
@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    /**
     * Finds a {@link Coach} by email.
     *
     * @param email The {@link Coach}'s email.
     * @return An {@link Optional} {@link Coach} object.
     */
    @Query(
            "SELECT c.password " +
            "FROM Coach c " +
            "WHERE c.id = :id")
    String findPasswordByCoachId(Long id);

    @Query(
            "SELECT c " +
            "FROM Coach c " +
            "WHERE c.email = :email")
    Optional<Coach> findByEmail(String email);

    /**
     * Finds {@link Competition} associated with a {@link Coach}.
     *
     * @param id The ID of the {@link Coach}.
     * @return A list of {@link Competition} objects.
     */
    @Query(
            "SELECT c " +
            "FROM Competition c " +
            "WHERE c.coach.id = :id ")
    List<Competition> findPersonalCompetitionById(Long id);

    /**
     * Finds {@link TrainingSession} associated with a {@link Coach}.
     *
     * @param id The ID of the {@link Coach}.
     * @return A list of {@link TrainingSession} objects.
     */
    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.coach.id = :id ")
    List<TrainingSession> findPersonalTrainingById(Long id);

}
