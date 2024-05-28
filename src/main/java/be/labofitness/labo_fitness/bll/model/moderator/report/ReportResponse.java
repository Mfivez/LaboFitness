package be.labofitness.labo_fitness.bll.model.moderator.report;

import java.util.List;

public record ReportResponse(
        List<String> reportedUserEmail,
        List<String> complainantUserEmail,
        List<String> description,
        List<Boolean> isApproved,
        List<Boolean> isConfirmed
) {
}
