package be.labofitness.labo_fitness.bll.model.user.makeReport;

/**
 * Represents the response model for making a report.
 * <p>
 * This record encapsulates a message indicating the result of the report operation.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String reportMessage = "Report successfully submitted.";
 *
 * ReportResponse response = new ReportResponse(reportMessage);
 * }</pre>
 *
 * @param ReportMessage The message indicating the result of the report operation.
 */
public record ReportResponse(
        String ReportMessage
) {

    public static ReportResponse fromEntity(String ReportMessage){
        return new ReportResponse(ReportMessage);
    }

}
