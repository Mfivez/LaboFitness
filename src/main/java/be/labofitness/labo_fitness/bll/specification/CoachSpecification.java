package be.labofitness.labo_fitness.bll.specification;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


/**
 * {@link Specification} class for filtering {@link Coach} entities based on various criteria.
 */
public abstract class CoachSpecification {

    /**
     * Specifies a condition to filter {@link Coach} by specialization.
     *
     * @param specialization the specialization to match
     * @return a {@link Specification} object for filtering {@link Coach} by specialization
     */
    public static Specification<Coach> hasSpecialization(String specialization) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("specialization")), "%" + specialization.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Coach} by remote service availability.
     *
     * @param isRemote the remote service availability to match
     * @return a {@link Specification} object for filtering {@link Coach} by remote service availability
     */
    public static Specification<Coach> isRemote(boolean isRemote) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isRemote"), isRemote);
    }

    /**
     * Specifies a condition to filter {@link Coach} by price per hour greaterThanOrEqualTo a value.
     *
     * @param priceHour the price per hour to match
     * @return a {@link Specification} object for filtering {@link Coach} by price per hour
     */
    public static Specification<Coach> hasPriceHourGreaterThanOrEqualTo(int priceHour) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("priceHour"), priceHour);
    }

    /**
     * Specifies a condition to filter {@link Coach} by price per hour LessThanOrEqualTo a value
     *
     * @param priceHour the price per hour to match
     * @return a {@link Specification} object for filtering {@link Coach} by price per hour
     */
    public static Specification<Coach> hasPriceHourLessThanOrEqualTo(int priceHour) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("priceHour"), priceHour);
    }



    /**
     * Specifies a condition to filter {@link Coach} by name.
     *
     * @param name the name to match
     * @return a {@link Specification} object for filtering {@link Coach} by name
     */
    public static Specification<Coach> hasName(String name) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Coach} by last name.
     *
     * @param lastName the last name to match
     * @return a {@link Specification} object for filtering {@link Coach} by last name
     */
    public static Specification<Coach> hasLastName(String lastName) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Coach} by email.
     *
     * @param email the email to match
     * @return a {@link Specification} object for filtering {@link Coach} by email
     */
    public static Specification<Coach> hasEmail(String email) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Coach> hasASubscriptionToTrainingOfCoach(Long clientId) {
        return (_, query, criteriaBuilder) -> {
            Root<TrainingSession> root1 = query.distinct(true).from(TrainingSession.class);
            return criteriaBuilder.equal(root1.get("clients").get("id"), clientId);
        };
    }

    public static Specification<Coach> hasASubscriptionToCompetitionOfCoach(Long clientId) {
        return (_, query, criteriaBuilder) -> {
            Root<Competition> root1 = query.distinct(true).from(Competition.class);
            return criteriaBuilder.equal(root1.get("client").get("id"), clientId);
        };
    }

}
