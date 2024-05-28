package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.model.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;


public interface ModeratorService {

    // region Report

    ReportResponse moderatorGetReport(ReportRequest request);

    ModeratorReportUpdateIsApprovedStateResponse moderatorUpdateIsApproved(ModeratorUpdateReportIsApprovedStateRequest request);

    // endregion


}
