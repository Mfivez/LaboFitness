package be.labofitness.labo_fitness.bll.model.moderator.report;

import be.labofitness.labo_fitness.domain.entity.Report;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public record ReportRequest(
    String reportedUserMail,
    String complainantMail,
    String isConfirmed, // TODO ne doit prendre que true false ou any
    String isApproved //TODO ne doit prendre que true false ou any
) {
}
