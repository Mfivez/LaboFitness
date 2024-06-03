package be.labofitness.labo_fitness.bll.model.user.getReport;

/**
 * Represents the response model for retrieving a report.
 * <p>
 * This record encapsulates a message containing the report details.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String reportMessage = "This is the report message.";
 *
 * GetReportResponse response = new GetReportResponse(reportMessage);
 * }</pre>
 *
 * @param ReportMessage The message containing the report details.
 */
public record GetReportResponse(
        String ReportMessage
) {

    public static GetReportResponse fromEntity(String ReportMessage){
        return new GetReportResponse(ReportMessage);
    }

}
