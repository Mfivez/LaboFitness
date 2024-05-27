package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.model.request.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.request.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.response.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.model.response.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;


public interface ModeratorService {

    // region Report

    ReportResponse moderatorGetReport(ReportRequest request);

    ModeratorReportUpdateIsApprovedStateResponse moderatorUpdateIsApproved(ModeratorUpdateReportIsApprovedStateRequest request);

    // endregion


}
