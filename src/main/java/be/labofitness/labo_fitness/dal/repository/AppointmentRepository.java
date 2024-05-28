package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>Repository interface for managing {@link Appointment} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 * <p>Extends {@link JpaSpecificationExecutor} to support dynamic query execution.</p>
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    /**
     * Counts the number of {@link Appointment} for a given {@link Client} with a specific status.
     *
     * @param client the {@link Client} for whom the count is being performed
     * @param status the status of the {@link Appointment} to count
     * @return the number of {@link Appointment} for the given {@link Client} with the specified status
     */
    @Query(
            "SELECT COUNT(a) " +
            "FROM Appointment a " +
            "JOIN a.client c " +
            "WHERE c = :client " +
            "AND a.appointmentStatus = :status")
    long countByClientAndAppointmentStatus(Client client, AppointmentStatus status);

    /**
     * Checks if there exists an {@link Appointment} for a given {@link Client} with a specific reason and status.
     *
     * @param client              the {@link Client} for whom the check is being performed
     * @param reasonOfAppointment the reason of the {@link Appointment} to check
     * @param status              the status of the {@link Appointment} to check
     * @return true if such an {@link Appointment} exists, false otherwise
     */
    @Query(
            "SELECT COUNT(a) > 0 " +
            "FROM Appointment a " +
            "JOIN a.client c " +
            "WHERE c = :client " +
            "AND a.reasonOfAppointment = :reasonOfAppointment " +
            "AND a.appointmentStatus = :status")
    boolean existsByClientAndReasonOfAppointmentAndAppointmentStatus(Client client, String reasonOfAppointment, AppointmentStatus status);

}
