package be.labofitness.labo_fitness.bll.model.moderator.report;
import java.util.List;

/**
 * Represents the response model for reporting a user to a moderator.
 * <p>
 * This record encapsulates lists of reported user emails, complainant emails, descriptions, approval states, and confirmation states.
 * </p>
 *
 * @param reportedUserEmail    The list of reported user emails.
 * @param complainantUserEmail The list of complainant user emails.
 * @param description          The list of descriptions.
 * @param isApproved           The list of approval states.
 * @param isConfirmed          The list of confirmation states.
 */
public record ReportResponse(
        List<String> reportedUserEmail,
        List<String> complainantUserEmail,
        List<String> description,
        List<Boolean> isApproved,
        List<Boolean> isConfirmed
) {
}
