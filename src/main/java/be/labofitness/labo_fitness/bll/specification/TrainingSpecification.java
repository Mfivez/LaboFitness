package be.labofitness.labo_fitness.bll.specification;

import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public abstract class TrainingSpecification {

    public static Specification<TrainingSession> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<TrainingSession> hasDuration(int duration) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("duration"), duration);

    }

    public static Specification<TrainingSession> hasStartDateAfter(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<TrainingSession> hasEndDateBefore(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }

    public static Specification<TrainingSession> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("description"), description);
    }

    public static Specification<TrainingSession> isInscriptionOpen(boolean isOpen) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isInscriptionOpen"), isOpen);
    }

    public static Specification<TrainingSession> hasRecommendedLevel(RecommendedLevel level) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recommended_level"), level);
    }

    public static Specification<TrainingSession> hasCoach(Long coachId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("coach").get("id"), coachId);
    }

    public static Specification<TrainingSession> hasClient(Long clientId) {
        return (root, query, criteriaBuilder) -> {
            Join<TrainingSession, Client> clientJoin = root.join("clients"); // Join sur l'attribut clients
            return criteriaBuilder.equal(clientJoin.get("id"), clientId);
        };
    }

    // Ajoutez d'autres spécifications selon vos besoins
}

