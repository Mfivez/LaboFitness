package be.labofitness.labo_fitness.bll.model.user.makeReport;
import be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator.EmailValid;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for making a report.
 * <p>
 * This record encapsulates the email of the reported user and the description of the report.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String userEmail = "example@example.com";
 * String reportDescription = "This is a report description.";
 *
 * MakeReportRequest request = new MakeReportRequest(userEmail, reportDescription);
 * }</pre>
 *
 * @param reportedUserEmail The email of the reported user.
 * @param report            The description of the report.
 */
public record MakeReportRequest(

        @NotNull(message = "error.report.reportedUserEmail.null")
        @EmailValid
        String reportedUserEmail,

        @NotNull(message = "error.report.reportDescription.null")
        @StringValid(entity = "report", field = "reportDescription", min = 25)
        String report
) {
}
