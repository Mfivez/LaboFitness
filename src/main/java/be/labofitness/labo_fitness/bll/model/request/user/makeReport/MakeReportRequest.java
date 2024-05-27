package be.labofitness.labo_fitness.bll.model.request.user.makeReport;

public record MakeReportRequest(
        String reportedUserEmail,
        String report
) {
}
