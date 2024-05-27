package be.labofitness.labo_fitness.bll.model.response.moderator.report;

import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public record ReportResponse(
        List<String> reportedUserEmail,
        List<String> complainantUserEmail,
        List<String> description,
        List<Boolean> isApproved,
        List<Boolean> isConfirmed
) {
}
