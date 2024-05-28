package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * <p>Repository interface for managing {@link Client} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 *
 *
 * <p>This repository provides methods for accessing various aspects related to clients, including:</p>
 * <code>
 * <ul>
 *   <li>Personal Training Sessions</li>
 *   <li>Personal Coaches</li>
 *   <li>Personal Physiotherapists</li>
 *   <li>Personal Competitions</li>
 *   <li>Personal Appointments</li>
 *   <li>Utility Methods</li>
 * </ul>
 * </code>
 *
 * <p>Each method is tailored to retrieve specific information based on client-related entities and their relationships.</p>
 * {@code Maybe need a refactoring where we'll use specifications}
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    //regon PASSWORD MANAGEMENT

    @Query("SELECT c.password FROM Client c WHERE c.email ilike :email")
    String findPasswordByClientEmail(String email);

    @Query("SELECT c.password FROM Client c where c.id = :id")
    String findPasswordByClientId(Long id);

    //endregion


    //region UTILS

    @Query( "Select c " +
            "from Client c " +
            "where c.email ilike :email")
    Optional<Client> findByEmail(String email);

    //endregion

    // region PERSONAL TRAINING SESSION

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client}.
     *
     * @param clientId the ID of the {@link Client}
     * @return a list of personal {@link TrainingSession}
     */
    @Query(
            "SELECT c.trainingSessions " +
            "FROM Client c " +
            "WHERE c.id = :clientId")
    List<TrainingSession> findPersonalTrainingSessions(Long clientId);

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on recommended level.
     *
     * @param clientId the ID of the {@link Client}
     * @param recommendedLevel  the recommended level
     * @return a list of personal {@link TrainingSession}
     */
    @Query(
            "SELECT t " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE c.id = :clientId " +
            "AND t.recommendedLevel = :recommendedLevel")
    List<TrainingSession> findPersonalTrainingSessionsByRecommendedLevel(Long clientId, RecommendedLevel recommendedLevel);

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on duration.
     *
     * @param clientId  the ID of the {@link Client}
     * @param duration  the duration of the {@link TrainingSession}
     * @return a list of personal {@link TrainingSession}
     */
    @Query(
            "SELECT t " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE c.id = :clientId " +
            "AND t.duration = :duration")
    List<TrainingSession> findPersonalTrainingSessionsByDuration(Long clientId, int duration);

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on name.
     *
     * @param clientId  the ID of the {@link Client}
     * @param name      the name of the {@link TrainingSession}
     * @return a list of personal {@link TrainingSession}
     */
    @Query(
            "SELECT t " +
                    "FROM Client c " +
                    "JOIN c.trainingSessions t " +
                    "WHERE c.id = :clientId " +
                    "AND t.name = :name")
    List<TrainingSession> findPersonalTrainingSessionsByName(Long clientId, String name);

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on {@link Coach}'s name.
     *
     * @param clientId      the ID of the {@link Client}
     * @param coachName     the name of the {@link Coach}
     * @return a list of personal {@link TrainingSession}
     */
    @Query(
            "SELECT t " +
                    "FROM Client c " +
                    "JOIN c.trainingSessions t " +
                    "WHERE c.id = :clientId " +
                    "AND t.coach.name = :coachName")
    List<TrainingSession> findPersonalTrainingSessionsByCoachName(Long clientId, String coachName);

    // endregion

    //region PERSONAL COACH

    /**
     * Retrieves all personal {@link Coach} of a {@link Client}.
     *
     * @param clientId the ID of the {@link Client}
     * @return a list of personal {@link Coach}
     */
    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE c.id = :clientId")
    List<Coach> findAllPersonalCoaches(Long clientId);

    /**
     * Retrieves personal {@link Coach} of a {@link Client} based on whether they are remote or not.
     *
     * @param clientId  the ID of the {@link Client}
     * @param isRemote  boolean indicating whether the {@link Coach} is remote
     * @return a list of personal {@link Coach}
     */
    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE t.coach.isRemote = :isRemote " +
            "AND c.id = :clientId")
    List<Coach> findPersonalCoachesByIsRemote(Long clientId, boolean isRemote);

    /**
     * Retrieves personal {@link Coach} of a {@link Client} based on {@link Coach}'s name.
     *
     * @param clientId  the ID of the {@link Client}
     * @param coachName the name of the {@link Coach}
     * @return a list of personal {@link Coach}
     */
    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE t.coach.name = :coachName " +
            "AND c.id = :clientId")
    List<Coach> findPersonalCoachesByName(Long clientId, String coachName);

    /**
     * Retrieves personal {@link Coach} of a {@link Client} based on {@link Coach}'s specialization.
     *
     * @param clientId       the ID of the {@link Client}
     * @param specialization the specialization of the {@link Coach}
     * @return a list of personal {@link Coach}
     */
    @Query(
            "SELECT t.coach " +
            "FROM Client c " +
            "JOIN c.trainingSessions t " +
            "WHERE t.coach.specialization = :specialization " +
            "AND c.id = :clientId")
    List<Coach> findPersonalCoachesBySpecialization(Long clientId, String specialization);

    // endregion

    //region PERSONAL PHYSIOTHERAPIST

    /**
     * Retrieves all personal {@link Physiotherapist} of a {@link Client}.
     *
     * @param clientId the ID of the {@link Client}
     * @return a list of personal {@link Physiotherapist}
     */
    @Query(
            "SELECT a.physiotherapist " +
            "FROM Appointment a " +
            "WHERE a.client.id = :clientId")
    List<Physiotherapist> findAllPersonalPhysio(Long clientId);

    /**
     * Retrieves personal {@link Physiotherapist} of a {@link Client} based on {@link Physiotherapist}'s name.
     *
     * @param clientId the ID of the {@link Client}
     * @param physiotherapistName the name of the {@link Physiotherapist}
     * @return a list of personal {@link Physiotherapist}
     */
    @Query(
            "SELECT a.physiotherapist " +
            "FROM Appointment a " +
            "WHERE a.client.id = :clientId " +
            "AND a.physiotherapist.name = :physiotherapistName")
    List<Physiotherapist> findPersonalPhysioByName(Long clientId, String physiotherapistName);

    /**
     * Retrieves personal {@link Physiotherapist} of a {@link Client} based on {@link Physiotherapist}'s specialization.
     *
     * @param clientId the ID of the {@link Client}
     * @param specialization the specialization of the {@link Physiotherapist}
     * @return a list of personal {@link Physiotherapist}
     */
    @Query(
            "SELECT a.physiotherapist " +
                    "FROM Appointment a " +
                    "WHERE a.client.id = :clientId " +
                    "AND a.physiotherapist.specialization = :specialization")
    List<Physiotherapist> findPersonalPhysioBySpecialization(Long clientId, String specialization);

    //endregion

    // region PERSONAL COMPETITION

    /**
     * Retrieves {@link Competition} associated with a {@link Client}.
     *
     * @param clientId the ID of the {@link Client}
     * @return a list of {@link Competition} associated with the {@link Client}
     */
    @Query(
            "SELECT c.competitions " +
            "FROM Client c " +
            "WHERE c.id = :clientId")
    List<Competition> findCompetitionsByClientId(Long clientId);

    // endregion

    // region APPOINTMENT

    /**
     * Retrieves {@link Appointment} with a specific status associated with a {@link Client}.
     *
     * @param clientId          the ID of the {@link Client}
     * @param appointmentStatus the status of the {@link Appointment}
     * @return a list of {@link Appointment} with the specified status associated with the {@link Client}
     */
    @Query(
            "SELECT a " +
            "FROM Appointment a " +
            "WHERE a.client.id = :clientId " +
            "AND a.appointmentStatus = :appointmentStatus")
    List<Appointment> findAcceptAppointmentsByClientId(Long clientId, AppointmentStatus appointmentStatus);

    //TODO Bouger ce truc dans appointment repo
    /**
     * Retrieves an {@link Appointment} by its ID.
     *
     * @param appointmentId the ID of the {@link Appointment}
     * @return the {@link Appointment} with the specified ID, if found
     */
    @Query(
            "SELECT a " +
            "FROM Appointment a " +
            "WHERE a.id = :appointmentId")
    Appointment getAppointmentById(Long appointmentId);

    // endregion

    //region UTILS

    /**
     * Finds a {@link Client} by email.
     *
     * @param email the email of the {@link Client}
     * @return an {@link Optional} containing the {@link Client} with the specified email, if found
     */
    @Query( "Select c " +
            "from Client c " +
            "where c.email ilike :email")
    Optional<Client> findByEmail(String email);

    //endregion

}
