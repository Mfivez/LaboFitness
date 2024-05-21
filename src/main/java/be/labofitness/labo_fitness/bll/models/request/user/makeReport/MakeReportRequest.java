package be.labofitness.labo_fitness.bll.models.request.user.makeReport;

public record MakeReportRequest(
        String reportedUserEmail,
        String report
) {
}
