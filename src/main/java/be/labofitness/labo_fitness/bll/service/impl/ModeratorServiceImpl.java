package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.service.service.ModeratorService;
import be.labofitness.labo_fitness.bll.specification.ReportSpecification;
import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ModeratorServiceImpl implements ModeratorService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Override
    public ReportResponse moderatorGetReport(ReportRequest request) {
        Specification<Report> spec = Specification.where(null);

        if (request.isConfirmed() != null && !request.isConfirmed().isEmpty()) {
            if (request.isConfirmed().equalsIgnoreCase("true")) {
                spec = spec.and(ReportSpecification.isConfirmed(true));
            }
            else if (request.isConfirmed().equalsIgnoreCase("false")) {
                spec = spec.and(ReportSpecification.isConfirmed(false));
            }
            else if (request.isConfirmed().equalsIgnoreCase("any")) {
                spec = spec.and(Specification.where(ReportSpecification.isConfirmed(true))
                        .or(ReportSpecification.isConfirmed(false)));
            }
            else {
                throw new IllegalArgumentException("isConfirmed must be true, false, or any");
            }
        }

        if (request.isApproved() != null && !request.isApproved().isEmpty()) {
            if (request.isApproved().equalsIgnoreCase("true")) {
                spec = spec.and(ReportSpecification.isApproved(true));
            }
            else if (request.isApproved().equalsIgnoreCase("false")) {
                spec = spec.and(ReportSpecification.isApproved(false));
            }
            else if (request.isApproved().equalsIgnoreCase("any")) {
                spec = spec.and(Specification.where(ReportSpecification.isApproved(true))
                        .or(ReportSpecification.isApproved(false)));
            }
            else {
                throw new IllegalArgumentException("isApproved must be true, false, or any");
            }
        }

        if (request.reportedUserMail() != null && !request.reportedUserMail().isEmpty()) {
            spec = spec.and(ReportSpecification.hasReportedUser(
                    userRepository.findByEmail(request.reportedUserMail())
                            .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"))
                            .getId()
            ));
        }

        if (request.complainantMail() != null && !request.complainantMail().isEmpty()) {
            spec = spec.and(ReportSpecification.hasComplainant(
                    userRepository.findByEmail(request.complainantMail())
                            .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"))
                            .getId()
            ));
        }

        List<Report> reports = reportRepository.findAll(spec);
        List<String> reportedUserEmail = reports.stream().map(Report::getReportedUser).map(User::getEmail).collect(Collectors.toList());
        List<String> complainantUserEmail = reports.stream().map(Report::getComplainant).map(User::getEmail).collect(Collectors.toList());
        List<String> description = reports.stream().map(Report::getDescription).collect(Collectors.toList());
        List<Boolean> isApproved2 = reports.stream().map(Report::isApproved).collect(Collectors.toList());
        List<Boolean> isConfirmed2 = reports.stream().map(Report::isConfirmed).collect(Collectors.toList());

        return new ReportResponse(reportedUserEmail, complainantUserEmail, description, isApproved2, isConfirmed2);
    }

    @Override
    public ModeratorReportUpdateIsApprovedStateResponse moderatorUpdateIsApproved(ModeratorUpdateReportIsApprovedStateRequest request) {
        String message;
        Report report = reportRepository.findById(request.reportId())
                                        .orElseThrow(() -> new IllegalArgumentException("Report doesn't exist"));

        if (report.isApproved() == request.isApprovedState()) {
            message = "Report approved statement is already " + request.isApprovedState();
        }
        else {
            report.setApproved(request.isApprovedState());
            reportRepository.save(report);
            message = "Report approved statement is now " + request.isApprovedState();
        }

        return new ModeratorReportUpdateIsApprovedStateResponse( message, report.getId());
    }
}
