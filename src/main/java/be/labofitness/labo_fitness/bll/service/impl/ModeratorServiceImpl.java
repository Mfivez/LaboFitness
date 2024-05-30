package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.service.service.ModeratorService;
import be.labofitness.labo_fitness.bll.service.service.ReportService;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import be.labofitness.labo_fitness.bll.specification.ReportSpecification;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ModeratorService} interface.
 * <br>Provides operations for moderators to manage reports.
 */
@RequiredArgsConstructor
@Service
public class ModeratorServiceImpl implements ModeratorService {

    private final ReportService reportService;
    private final UserService userService;

    /**
     * Retrieves {@link Report} based on the provided criteria.
     *
     * @param request the request containing filtering criteria
     * @return a response containing the retrieved {@link Report}
     * @throws IllegalArgumentException if invalid filtering criteria are provided
     */
    @Override
    public ReportResponse moderatorGetReport(ReportRequest request) {
        Specification<Report> spec = Specification.where(null);

        spec = getIsApprovedStatusReport(spec, request.isApproved());
        spec = getIsConfirmedStatusReport(spec, request.isConfirmed());
        spec = getSpecificationReportedUserMail(spec, request.reportedUserMail());
        spec = getSpecificationComplainantMail(spec, request.complainantMail());

        List<Report> reports =reportService.getReportsBySpecification(spec);
        List<String> reportedUserEmail = reports.stream().map(Report::getReportedUser).map(User::getEmail).collect(Collectors.toList());
        List<String> complainantUserEmail = reports.stream().map(Report::getComplainant).map(User::getEmail).collect(Collectors.toList());
        List<String> description = reports.stream().map(Report::getDescription).collect(Collectors.toList());
        List<Boolean> isApproved2 = reports.stream().map(Report::isApproved).collect(Collectors.toList());
        List<Boolean> isConfirmed2 = reports.stream().map(Report::isConfirmed).collect(Collectors.toList());

        return new ReportResponse(reportedUserEmail, complainantUserEmail, description, isApproved2, isConfirmed2);
    }


    /**
     * Returns a {@link Specification} for filtering {@link Report} based on the approved status.
     *
     * @param spec   the base {@link Specification} to which the approved status filter will be added
     * @param status the approved status to filter by (true, false, or any)
     * @return a {@link Specification} filtering {@link Report} based on the approved status
     * @throws IllegalArgumentException if an invalid approved status is provided
     */
    private Specification<Report> getIsApprovedStatusReport(Specification<Report> spec, String status) {
        if (status != null && !status.isEmpty()) {
            if (status.equalsIgnoreCase("true")) {
                spec = spec.and(ReportSpecification.isConfirmed(true));
            } else if (status.equalsIgnoreCase("false")) {
                spec = spec.and(ReportSpecification.isConfirmed(false));
            } else if (status.equalsIgnoreCase("any")) {
                spec = spec.and(Specification.where(ReportSpecification.isConfirmed(true))
                        .or(ReportSpecification.isConfirmed(false)));
            } else {
                throw new IllegalArgumentException("isConfirmed must be true, false, or any");
            }
        }
        return spec;
    }

    /**
     * Returns a {@link Specification} for filtering {@link Report} based on the confirmed status.
     *
     * @param spec   the base {@link Specification} to which the confirmed status filter will be added
     * @param status the confirmed status to filter by (true, false, or any)
     * @return a {@link Specification} filtering {@link Report} based on the confirmed status
     * @throws IllegalArgumentException if an invalid confirmed status is provided
     */
    private Specification<Report> getIsConfirmedStatusReport(Specification<Report> spec, String status) {
        if (status != null && !status.isEmpty()) {
            if (status.equalsIgnoreCase("true")) {
                spec = spec.and(ReportSpecification.isApproved(true));
            } else if (status.equalsIgnoreCase("false")) {
                spec = spec.and(ReportSpecification.isApproved(false));
            } else if (status.equalsIgnoreCase("any")) {
                spec = spec.and(Specification.where(ReportSpecification.isApproved(true))
                        .or(ReportSpecification.isApproved(false)));
            } else {
                throw new IllegalArgumentException("isApproved must be true, false, or any");
            }
        }
        return spec;
    }

    /**
     * Returns a {@link Specification} for filtering {@link Report} based on the reported user's email.
     *
     * @param spec the base {@link Specification} to which the reported user's email filter will be added
     * @param mail the email of the reported user to filter by
     * @return a {@link Specification} filtering {@link Report} based on the reported user's email
     * @throws IllegalArgumentException if the specified email does not correspond to an existing user
     */
    private Specification<Report> getSpecificationReportedUserMail(Specification<Report> spec, String mail) {
        if (mail != null && !mail.isEmpty()) {
            spec = spec.and(ReportSpecification.hasReportedUser(userService.getOneByEmail(mail).getId()));
        }
        return spec;
    }

    /**
     * Returns a {@link Specification} for filtering {@link Report} based on the complainant's email.
     *
     * @param spec the base {@link Specification} to which the complainant's email filter will be added
     * @param mail the email of the complainant to filter by
     * @return a {@link Specification} filtering {@link Report} based on the complainant's email
     * @throws IllegalArgumentException if the specified email does not correspond to an existing user
     */
    private Specification<Report> getSpecificationComplainantMail(Specification<Report> spec, String mail) {
        if (mail != null && !mail.isEmpty()) {
            spec = spec.and(ReportSpecification.hasComplainant( userService.getOneByEmail(mail).getId()));
        }
        return spec;
    }


    /**
     * Updates the approved state of a {@link Report}.
     *
     * @param request the request containing the {@link Report} ID and the new approved state
     * @return a response indicating the outcome of the operation
     * @throws IllegalArgumentException if the specified {@link Report} doesn't exist
     */
    @Override
    public ModeratorReportUpdateIsApprovedStateResponse moderatorUpdateIsApproved(ModeratorUpdateReportIsApprovedStateRequest request) {
        String message;
        Report report = reportService.getOne(request.reportId());

        if (report.isApproved() == request.isApprovedState()) {
            message = "Report approved statement is already " + request.isApprovedState();
        }
        else {
            report.setApproved(request.isApprovedState());
            reportService.update(report);
            message = "Report approved statement is now " + request.isApprovedState();
        }

        return new ModeratorReportUpdateIsApprovedStateResponse( report.getId(), message);
    }
}
