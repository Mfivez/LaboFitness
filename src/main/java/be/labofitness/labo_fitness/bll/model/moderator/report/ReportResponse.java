package be.labofitness.labo_fitness.bll.model.moderator.report;

import be.labofitness.labo_fitness.domain.entity.Report;

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
        String reportedUserEmail,
        String complainantUserEmail,
        String description,
        Boolean isApproved,
        Boolean isConfirmed
) {

    public static ReportResponse fromEntity(Report report){
        return new ReportResponse(
                report.getReportedUser().getName(),
                report.getComplainant().getName(),
                report.getDescription(),
                report.isApproved(),
                report.isConfirmed()
        );
    }
}
