package be.labofitness.labo_fitness.bll.specification;

import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public abstract class AppointmentSpecification {

    public static Specification<Appointment> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Appointment> hasClient(Long clientId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }

    public static Specification<Appointment> hasPhysiotherapist(Long physiotherapistId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("physiotherapist").get("id"), physiotherapistId);
    }

    public static Specification<Appointment> hasStartDateAfter(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<Appointment> hasEndDateAfter(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), endDate);
    }

    public static Specification<Appointment> hasStatus(AppointmentStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("appointmentStatus"), status);
    }


}
