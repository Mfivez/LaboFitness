package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Repository interface for managing {@link Physiotherapist} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 */
@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long> {

    /**
     * Retrieves the list of personal accepted {@link Appointment} for a {@link Physiotherapist}.
     *
     * @param id     The ID of the {@link Physiotherapist}.
     * @param status The status of the {@code appointment}.
     * @return A list of {@link Appointment} objects representing personal accepted {@code appointments}.
     */
    @Query(
            "SELECT a " +
            "FROM Appointment a " +
            "WHERE a.physiotherapist.id = :id " +
            "AND a.appointmentStatus = :status")
    List<Appointment> findPersonalAcceptedAppointments(Long id, AppointmentStatus status);

    /**
     * Finds a {@link Physiotherapist} by email.
     *
     * @param email The email of the {@link Physiotherapist}.
     * @return An {@link Optional} {@link Physiotherapist} object.
     */
    @Query(
            "SELECT p " +
            "FROM Physiotherapist p " +
            "WHERE p.email = :email")
    Optional<Physiotherapist> findByEmail(String email);

}
