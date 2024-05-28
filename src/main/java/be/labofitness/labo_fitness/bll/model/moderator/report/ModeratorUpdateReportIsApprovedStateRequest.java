package be.labofitness.labo_fitness.bll.model.moderator.report;

public record ModeratorUpdateReportIsApprovedStateRequest(
        Long reportId,
        boolean isApprovedState
) {
}
