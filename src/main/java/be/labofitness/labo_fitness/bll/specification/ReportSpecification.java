package be.labofitness.labo_fitness.bll.specification;

import be.labofitness.labo_fitness.domain.entity.Report;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public abstract class ReportSpecification {

    public static Specification<Report> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Report> hasReportedUser(Long reportedUserId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("reportedUser").get("id"), reportedUserId);
    }

    public static Specification<Report> hasComplainant(Long complainantId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("complainant").get("id"), complainantId);
    }

    public static Specification<Report> hasDateAfter(LocalDateTime date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), date);
    }

    public static Specification<Report> isConfirmed(boolean isConfirmed) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isConfirmed"), isConfirmed);
    }

    public static Specification<Report> isApproved(boolean isApproved) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isApproved"), isApproved);
    }

}
