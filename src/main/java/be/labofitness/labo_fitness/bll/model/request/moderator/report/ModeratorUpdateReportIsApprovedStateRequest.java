package be.labofitness.labo_fitness.bll.model.request.moderator.report;

public record ModeratorUpdateReportIsApprovedStateRequest(
        Long reportId,
        boolean isApprovedState
) {
}
