package be.labofitness.labo_fitness.bll.specification;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

/**
 * {@link Specification} class for filtering {@link Physiotherapist} entities based on various criteria.
 */
public abstract class PhysioSpecification {

    /**
     * Specifies a condition to filter {@link Physiotherapist} by specialization.
     *
     * @param specialization the specialization to match
     * @return a {@link Specification} object for filtering {@link Physiotherapist} by specialization
     */
    public static Specification<Physiotherapist> hasSpecialization(String specialization) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("specialization")), "%" + specialization.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Physiotherapist} by INAMI number.
     *
     * @param inamiNumber the INAMI number to match
     * @return a {@link Specification} object for filtering {@link Physiotherapist} by INAMI number
     */
    public static Specification<Physiotherapist> hasInamiNumber(long inamiNumber) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("inamiNumber"), inamiNumber);
    }

    /**
     * Specifies a condition to filter {@link Physiotherapist} by name.
     *
     * @param name the name to match
     * @return a {@link Specification} object for filtering {@link Physiotherapist} by name
     */
    public static Specification<Physiotherapist> hasName(String name) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Physiotherapist} by last name.
     *
     * @param lastName the last name to match
     * @return a {@link Specification} object for filtering {@link Physiotherapist} by last name
     */
    public static Specification<Physiotherapist> hasLastName(String lastName) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastname")), "%" + lastName.toLowerCase() + "%");
    }

    /**
     * Specifies a condition to filter {@link Physiotherapist} by email.
     *
     * @param email the email to match
     * @return a {@link Specification} object for filtering {@link Physiotherapist} by email
     */
    public static Specification<Physiotherapist> hasEmail(String email) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }

    /**
     * Specifies a condition to filter {@link Physiotherapist} based on the client's ID.
     *
     * @param clientId the ID of the client
     * @return a {@link Specification} object for filtering {@link Physiotherapist} by client ID
     */
    public static Specification<Physiotherapist> hasClientId(Long clientId) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Appointment> appointmentRoot = subquery.from(Appointment.class);
            subquery.select(appointmentRoot.get("physiotherapist").get("id"))
                    .where(criteriaBuilder.equal(appointmentRoot.get("client").get("id"), clientId));
            return criteriaBuilder.in(root.get("id")).value(subquery);
        };

    }

}

