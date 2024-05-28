package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long> {

    @Query(
            "SELECT a " +
            "FROM Appointment a " +
            "WHERE a.physiotherapist.id = :id " +
            "AND a.appointmentStatus = :status")
    List<Appointment> findPersonalAcceptedAppointments(Long id, AppointmentStatus status);

    @Query(
            "SELECT p " +
            "FROM Physiotherapist p " +
            "WHERE p.email = :email")
    Optional<Physiotherapist> findByEmail(String email);

    @Query("SELECT p.password FROM Physiotherapist p WHERE p.id = :id")
    String findPasswordByPhysiotherapistId(Long id);
}
