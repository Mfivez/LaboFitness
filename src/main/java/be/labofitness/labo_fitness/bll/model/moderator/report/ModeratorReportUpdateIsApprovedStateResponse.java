package be.labofitness.labo_fitness.bll.model.moderator.report;

import be.labofitness.labo_fitness.domain.entity.Report;

/**
 * Represents the response model for updating the approval state of a report by a moderator.
 * <p>
 * This record encapsulates the ID of the report and an optional message.
 * </p>
 * <p>Example usage:</p>
 * <pre>{@code
 * Long id = 123L;
 * String message = "The report has been approved.";
 * ModeratorReportUpdateIsApprovedStateResponse response = new ModeratorReportUpdateIsApprovedStateResponse(id, message);
 * }</pre>
 *
 * @param Id      The ID of the report.
 * @param message An optional message regarding the report.
 */
public record ModeratorReportUpdateIsApprovedStateResponse(
        Long Id,
        String message

) {

    public static ModeratorReportUpdateIsApprovedStateResponse fromEntity(Report report, String message){
        return new ModeratorReportUpdateIsApprovedStateResponse(
                report.getId(),
                message + " successfully done"
        );
    }

}
