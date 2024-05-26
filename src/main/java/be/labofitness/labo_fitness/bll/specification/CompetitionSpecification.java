package be.labofitness.labo_fitness.bll.specification;

import be.labofitness.labo_fitness.domain.entity.Competition;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public abstract class CompetitionSpecification {

    public static Specification<Competition> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Competition> isInscriptionOpen(boolean isOpen) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("inscriptionIsOpen"), isOpen);
    }

    public static Specification<Competition> hasStartDateAfter(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<Competition> hasEndDateBefore(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }

    public static Specification<Competition> hasSport(String sportName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.join("sports").get("name")), "%" + sportName.toLowerCase() + "%");
    }

    public static Specification<Competition> hasCoach(Long coachId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("coach").get("id"), coachId);
    }

    public static Specification<Competition> hasClient(Long clientId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }
}
