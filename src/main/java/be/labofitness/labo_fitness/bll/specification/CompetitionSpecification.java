package be.labofitness.labo_fitness.bll.specification;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.Sport;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

/**
 * Specification class for filtering {@link Competition} entities based on various criteria.
 */
public abstract class CompetitionSpecification {

    /**
     * Specifies a condition to filter {@link Competition} by name.
     *
     * @param name the name to match
     * @return a {@link Specification} object for filtering {@link Competition} by name
     */
    public static Specification<Competition> hasName(String name) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Competition} by whether inscription is open.
     *
     * @param isOpen a boolean indicating whether inscription is open
     * @return a {@link Specification} object for filtering {@link Competition} by inscription status
     */
    public static Specification<Competition> isInscriptionOpen(boolean isOpen) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("inscriptionIsOpen"), isOpen);
    }

    /**
     * Specifies a condition to filter {@link Competition} by start date after a specified date.
     *
     * @param startDate the start date to filter by
     * @return a {@link Specification} object for filtering {@link Competition} by start date
     */
    public static Specification<Competition> hasStartDateAfter(LocalDateTime startDate) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    /**
     * Specifies a condition to filter {@link Competition} by end date before a specified date.
     *
     * @param endDate the end date to filter by
     * @return a {@link Specification} object for filtering {@link Competition} by end date
     */
    public static Specification<Competition> hasEndDateBefore(LocalDateTime endDate) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }

    /**
     * Specifies a condition to filter {@link Competition} by sport name.
     *
     * @param sportName the name of the {@link Sport} to match
     * @return a {@link Specification} object for filtering {@link Competition} by sport name
     */
    public static Specification<Competition> hasSport(String sportName) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.join("sports").get("name")), "%" + sportName.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Competition} by coach ID.
     *
     * @param coachId the ID of the {@link Coach}
     * @return a {@link Specification} object for filtering {@link Competition} by {@link Coach} ID
     */
    public static Specification<Competition> hasCoach(Long coachId) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("coach").get("id"), coachId);
    }

    /**
     * Specifies a condition to filter {@link Competition} by {@link Client} ID.
     *
     * @param clientId the ID of the {@link Client}
     * @return a {@link Specification} object for filtering {@link Competition} by {@link Client} ID
     */
    public static Specification<Competition> hasClient(Long clientId) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }

}
