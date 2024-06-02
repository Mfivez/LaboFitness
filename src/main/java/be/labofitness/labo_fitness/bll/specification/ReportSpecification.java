package be.labofitness.labo_fitness.bll.specification;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification class for filtering {@link Report} entities based on various criteria.
 */
public abstract class ReportSpecification {

    /**
     * Specifies a condition to filter {@link Report} by reported {@link User} ID.
     *
     * @param reportedUserId the ID of the reported {@link User}
     * @return a {@link Specification} object for filtering {@link Report} by reported {@link User} ID
     */
    public static Specification<Report> hasReportedUser(Long reportedUserId) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("reportedUser").get("id"), reportedUserId);
    }

    /**
     * Specifies a condition to filter {@link Report} by complainant ID.
     *
     * @param complainantId the ID of the complainant
     * @return a {@link Specification} object for filtering {@link Report} by complainant ID
     */
    public static Specification<Report> hasComplainant(Long complainantId) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("complainant").get("id"), complainantId);
    }

    /**
     * Specifies a condition to filter {@link Report} by approval status.
     *
     * @param isApproved a boolean indicating whether the {@link Report} is approved
     * @return a {@link Specification} object for filtering {@link Report} by approval status
     */

    public static Specification<Report> isApproved(boolean isApproved) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isApproved"), isApproved);
    }

}
