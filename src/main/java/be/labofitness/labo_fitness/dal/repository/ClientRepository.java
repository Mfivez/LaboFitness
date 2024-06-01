package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.dal.util.HasFindByMethod;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
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
public interface ClientRepository extends JpaRepository<Client, Long>, HasFindByMethod<Client> {


    //region PASSWORD MANAGEMENT

    @Query("SELECT c.password FROM Client c where c.id = :id")
    String findPasswordByClientId(Long id);

    //endregion

    // region APPOINTMENT

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
