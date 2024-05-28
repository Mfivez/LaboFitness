package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for managing {@link User} entities.
 * <br>Extends {@link JpaRepository} to inherit basic CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // region UTILS METHODS

    /**
     * Retrieves a {@link User} by their email address, ignoring case.
     *
     * @param email The email address of the {@link User}.
     * @return An optional containing the {@link User} if found, or empty otherwise.
     */
    @Query( "Select u " +
            "from User u " +
            "where u.email ilike :email")
    Optional<User> findByEmail(String email);

    //endregion

    //region REGISTER

    /**
     * Checks if a {@link User} with the given email address exists.
     *
     * @param email The email address to check.
     * @return True if a {@link User} with the email address exists, false otherwise.
     */
    @Query(
            "SELECT COUNT(*)>0 " +
            "FROM User  " +
            "WHERE email ILIKE :email" )
    boolean existsByEmail(String email);

    //endregion

    //region COACH

    /**
     * Retrieves all {@link Coach}.
     *
     * @return A list of all {@code coaches}.
     */
    @Query("SELECT c " +
            "FROM Coach c")
    List<Coach> findAllCoaches();

    /**
     * Retrieves {@link Coach} by their remote status.
     *
     * @param is_remote The remote status to filter by.
     * @return A list of {@code coaches} with the given remote status.
     */
    @Query(
            "SELECT c " +
            "FROM Coach c " +
            "WHERE c.isRemote = :is_remote")
    List<Coach> findCoachesByIsRemote(boolean is_remote);

    /**
     * Retrieves {@link Coach} by their name.
     *
     * @param coach_name The name of the {@link Coach}.
     * @return A list of {@code coaches} with the given name.
     */
    @Query(
            "SELECT c " +
            "FROM Coach c " +
            "WHERE c.name = :coach_name")
    List<Coach> findCoachesByName(String coach_name);

    /**
     * Retrieves {@link Coach} by their specialization.
     *
     * @param specialization The specialization of the {@link Coach}.
     * @return A list of {@code coaches} with the given specialization.
     */
    @Query(
            "SELECT c " +
                    "FROM Coach c " +
                    "WHERE c.specialization = :specialization")
    List<Coach> findCoachesBySpecialization(String specialization);

    // endregion

    //region PHYSIOTHERAPIST

    /**
     * Retrieves all {@link Physiotherapist}.
     *
     * @return A list of all {@code physiotherapists}.
     */
    @Query("SELECT p " +
            "FROM Physiotherapist p")
    List<Physiotherapist> findAllPhysio();

    /**
     * Retrieves {@link Physiotherapist} by their name.
     *
     * @param physiotherapist_name The name of the {@link Physiotherapist}.
     * @return A list of {@code physiotherapists} with the given name.
     */
    @Query(
            "SELECT p " +
                    "FROM Physiotherapist p " +
                    "WHERE p.name = :physiotherapist_name")
    List<Physiotherapist> findPhysioByName(String physiotherapist_name);

    /**
     * Retrieves {@link Physiotherapist} by their specialization.
     *
     * @param specialization The specialization of the {@link Physiotherapist}.
     * @return A list of {@code physiotherapists} with the given specialization.
     */
    @Query(
            "SELECT p " +
                    "FROM Physiotherapist p " +
                    "WHERE p.specialization = :specialization")
    List<Physiotherapist> findPhysioBySpecialization(String specialization);

    //endregion

    // region TRAINING SESSION

    /**
     * Retrieves all {@link TrainingSession}.
     *
     * @return A list of all {@code training sessions}.
     */
    @Query(
            "SELECT t " +
            "FROM TrainingSession t ")
    List<TrainingSession> findAllTrainingSessions();

    /**
     * Retrieves {@link TrainingSession} by their recommended level.
     *
     * @param recommendedLevel The recommended level of the {@link TrainingSession}.
     * @return A list of {@code training sessions} with the given recommended level.
     */
    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.recommendedLevel = :recommendedLevel ")
    List<TrainingSession> findTrainingSessionsByRecommendedLevel(RecommendedLevel recommendedLevel);

    /**
     * Retrieves {@link TrainingSession} by their duration.
     *
     * @param duration The duration of the {@link TrainingSession}.
     * @return A list of {@code training sessions} with the given duration.
     */
    @Query(
            "SELECT t " +
            "FROM TrainingSession t " +
            "WHERE t.duration = :duration")
    List<TrainingSession> findTrainingSessionsByDuration(int duration);

    /**
     * Retrieves {@link TrainingSession} by their name.
     *
     * @param name The name of the {@link TrainingSession}.
     * @return A list of {@code training sessions} with the given name.
     */
    @Query(
            "SELECT t " +
                    "FROM TrainingSession t " +
                    "WHERE t.name = :name")
    List<TrainingSession> findTrainingSessionsByName(String name);

    /**
     * Retrieves {@link TrainingSession} by the name of their coach.
     *
     * @param coachName The name of the coach associated with the {@link TrainingSession}.
     * @return A list of {@code training sessions} with the given coach name.
     */
    @Query(
            "SELECT t " +
                    "FROM TrainingSession t " +
                    "WHERE t.coach.name = :coachName")
    List<TrainingSession> findTrainingSessionsByCoachName(String coachName);


    // endregion

    // region REPORT

    /**
     * Retrieves the description of {@link Report} by their validation status for a given user.
     *
     * @param userReportedId The ID of the reported user.
     * @param isValidate     The validation status of the {@link Report}.
     * @return A set of {@link Report} descriptions that match the criteria.
     */
    @Query(
            "SELECT r.description " +
            "FROM Report r " +
            "JOIN r.reportedUser u " +
            "WHERE u.id = :userReportedId " +
            "AND r.isConfirmed = :isValidate ")
    Set<String> getReportMessageByIsValidate(Long userReportedId, boolean isValidate);

    //endregion

}


