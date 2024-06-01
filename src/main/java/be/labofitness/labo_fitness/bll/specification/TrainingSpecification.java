package be.labofitness.labo_fitness.bll.specification;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

/**
 * Specification class for filtering {@link TrainingSession} entities based on various criteria.
 */
public abstract class TrainingSpecification {

    /**
     * Specifies a condition to filter {@link TrainingSession} by name.
     *
     * @param name the name to match
     * @return a {@link Specification} object for filtering {@link TrainingSession} by name
     */
    public static Specification<TrainingSession> hasName(String name) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link TrainingSession} by start date after a specified date.
     *
     * @param startDate the start date to filter by
     * @return a {@link Specification} object for filtering {@link TrainingSession} by start date
     */
    public static Specification<TrainingSession> hasStartDateAfter(LocalDateTime startDate) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    /**
     * Specifies a condition to filter {@link TrainingSession} by end date before a specified date.
     *
     * @param endDate the end date to filter by
     * @return a {@link Specification} object for filtering {@link TrainingSession} by end date
     */
    public static Specification<TrainingSession> hasEndDateBefore(LocalDateTime endDate) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }

    /**
     * Specifies a condition to filter {@link TrainingSession} by {@link Coach} ID.
     *
     * @param coachId the ID of the {@link Coach}
     * @return a {@link Specification} object for filtering {@link TrainingSession} by {@link Coach} ID
     */
    public static Specification<TrainingSession> hasCoach(Long coachId) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("coach").get("id"), coachId);
    }

    /**
     * Specifies a condition to filter {@link TrainingSession} by {@link Client} ID.
     *
     * @param clientId the ID of the {@link Client}
     * @return a {@link Specification} object for filtering {@link TrainingSession} by {@link Client} ID
     */
    public static Specification<TrainingSession> hasClient(Long clientId) {
        return (root, _, criteriaBuilder) -> {
            Join<TrainingSession, Client> clientJoin = root.join("clients");
            return criteriaBuilder.equal(clientJoin.get("id"), clientId);
        };
    }

    public static Specification<TrainingSession> hasDuration(int duration) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("duration"), duration);
    }

    public static Specification<TrainingSession> hasRecommendedLevel(RecommendedLevel recommendedLevel) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recommendedLevel"), recommendedLevel);
    }

    public static Specification<TrainingSession> isInscriptionOpen(boolean isInscriptionOpen) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isInscriptionOpen"), isInscriptionOpen);
    }

}

