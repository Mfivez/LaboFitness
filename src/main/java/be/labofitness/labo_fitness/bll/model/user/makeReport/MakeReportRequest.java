package be.labofitness.labo_fitness.bll.model.user.makeReport;

public record MakeReportRequest(
        String reportedUserEmail,
        String report
) {
}
