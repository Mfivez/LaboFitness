package be.labofitness.labo_fitness.bll.model.moderator.report;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request model for updating the approval state of a report by a moderator.
 * <p>
 * This record encapsulates the ID of the report and the approval state.
 * </p>
 * <p>Example usage:</p>
 * <pre>{@code
 * Long reportId = 123L;
 * boolean isApprovedState = true;
 * ModeratorUpdateReportIsApprovedStateRequest request = new ModeratorUpdateReportIsApprovedStateRequest(reportId, isApprovedState);
 * }</pre>
 *
 * @param reportId        The ID of the report.
 * @param isApprovedState The approval state of the report.
 */
public record ModeratorUpdateReportIsApprovedStateRequest(

        @NotNull(message = "error.event.id.null")
        @NotBlank(message = "error.event.id.blank")
        @Min(value = 1)
        Long reportId,

        @StringValid(entity = "report", field = "message", min = 25)
        boolean isApprovedState
) {
}
