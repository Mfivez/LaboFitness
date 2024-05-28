package be.labofitness.labo_fitness.bll.specification;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

/**
 * {@link Specification} class for filtering {@link Appointment} entities based on various criteria.
 */
public abstract class AppointmentSpecification {

    /**
     * Specifies a condition to filter {@link Appointment} by name.
     *
     * @param name the name to match
     * @return a {@link Specification} object for filtering {@link Appointment} by name
     */
    public static Specification<Appointment> hasName(String name) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Appointment} by {@link Client} ID.
     *
     * @param clientId the ID of the client
     * @return a {@link Specification} object for filtering {@link Appointment} by {@link Client} ID
     */
    public static Specification<Appointment> hasClient(Long clientId) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }

    /**
     * Specifies a condition to filter {@link Appointment} by {@link Physiotherapist} ID.
     *
     * @param physiotherapistId the ID of the {@link Physiotherapist}
     * @return a {@link Specification} object for filtering {@link Appointment} by {@link Physiotherapist} ID
     */
    public static Specification<Appointment> hasPhysiotherapist(Long physiotherapistId) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("physiotherapist").get("id"), physiotherapistId);
    }

    /**
     * Specifies a condition to filter {@link Appointment} by start date after a specified date.
     *
     * @param startDate the start date to filter by
     * @return a {@link Specification} object for filtering {@link Appointment} by start date
     */
    public static Specification<Appointment> hasStartDateAfter(LocalDateTime startDate) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    /**
     * Specifies a condition to filter {@link Appointment} by end date after a specified date.
     *
     * @param endDate the end date to filter by
     * @return a {@link Specification} object for filtering {@link Appointment} by end date
     */
    public static Specification<Appointment> hasEndDateAfter(LocalDateTime endDate) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), endDate);
    }

    /**
     * Specifies a condition to filter {@link Appointment} by status.
     *
     * @param status the status to match
     * @return a {@link Specification} object for filtering {@link Appointment} by status
     */
    public static Specification<Appointment> hasStatus(AppointmentStatus status) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("appointmentStatus"), status);
    }

}
