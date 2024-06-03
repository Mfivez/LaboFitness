package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;
import be.labofitness.labo_fitness.domain.entity.Report;

import java.util.List;

/**
 * Service interface for managing {@code moderator access}.
 */
public interface ModeratorService {

    // region Report

    /**
     * Retrieves {@link Report} based on the provided request for moderation.
     *
     * @param request the {@link Report} request for moderation
     * @return the {@link Report} response containing information about the {@link Report}
     */
    List<ReportResponse> moderatorGetReport(ReportRequest request);

    /**
     * Updates the approval state of {@link Report} based on the provided request.
     *
     * @param request the request to update the approval state of {@link Report}
     * @return the response indicating the success of the update operation
     */
    ModeratorReportUpdateIsApprovedStateResponse moderatorUpdateIsApproved(ModeratorUpdateReportIsApprovedStateRequest request);

    // endregion

}
