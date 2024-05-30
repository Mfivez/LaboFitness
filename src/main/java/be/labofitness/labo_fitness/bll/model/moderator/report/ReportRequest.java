package be.labofitness.labo_fitness.bll.model.moderator.report;
import be.labofitness.labo_fitness.il.utils.annotations.anyBooleanValidator.AnyBooleanValid;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;

/**
 * Represents the request model for reporting a user to a moderator.
 * <p>
 * This record encapsulates the email of the reported user, the email of the complainant, and the confirmation and approval states.
 * </p>
 * <p>Example usage:</p>
 * <pre>{@code
 * String reportedUserMail = "user@example.com";
 * String complainantMail = "complainant@example.com";
 * String isConfirmed = "true";
 * String isApproved = "false";
 * ReportRequest request = new ReportRequest(reportedUserMail, complainantMail, isConfirmed, isApproved);
 * }</pre>
 *
 * @param reportedUserMail The email of the reported user.
 * @param complainantMail  The email of the complainant.
 * @param isConfirmed      The confirmation state.
 * @param isApproved       The approval state.
 */
public record ReportRequest(

    @EmailValid
    String reportedUserMail,

    @EmailValid
    String complainantMail,

    @AnyBooleanValid
    String isConfirmed,

    @AnyBooleanValid
    String isApproved

) {
}
