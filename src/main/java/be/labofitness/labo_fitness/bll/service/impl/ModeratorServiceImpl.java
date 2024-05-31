package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Unauthorize.UnauthorizedException;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.service.service.ModeratorService;
import be.labofitness.labo_fitness.bll.service.service.ReportService;
import be.labofitness.labo_fitness.bll.service.service.SpecificationService;
import be.labofitness.labo_fitness.bll.specification.ReportSpecification;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
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
    private final SpecificationService specificationService;
    private final UserRepository userRepository;

    /**
     * Retrieves {@link Report} based on the provided criteria.
     *
     * @param request the request containing filtering criteria
     * @return a response containing the retrieved {@link Report}
     * @throws UnauthorizedException if invalid filtering criteria are provided
     */
    @Override
    public ReportResponse moderatorGetReport(ReportRequest request) {
        Specification<Report> spec = Specification.where(null);

        spec = specificationService.specificationHasAnyBoolean(spec, request.isApproved(), ReportSpecification::isApproved);

        spec = specificationService.specificationHasAnyBoolean(spec, request.isConfirmed(), ReportSpecification::isApproved);

        spec = specificationService.specificationHasSomething(
                spec,
                specificationService.getIdByMail(request.reportedUserMail(), userRepository),
                ReportSpecification::hasReportedUser);

        spec = specificationService.specificationHasSomething(
                spec,
                specificationService.getIdByMail(request.complainantMail(), userRepository),
                ReportSpecification::hasComplainant);

        List<Report> reports =reportService.getReportsBySpecification(spec);
        List<String> reportedUserEmail = reports.stream().map(Report::getReportedUser).map(User::getEmail).collect(Collectors.toList());
        List<String> complainantUserEmail = reports.stream().map(Report::getComplainant).map(User::getEmail).collect(Collectors.toList());
        List<String> description = reports.stream().map(Report::getDescription).collect(Collectors.toList());
        List<Boolean> isApproved2 = reports.stream().map(Report::isApproved).collect(Collectors.toList());
        List<Boolean> isConfirmed2 = reports.stream().map(Report::isConfirmed).collect(Collectors.toList());

        return new ReportResponse(reportedUserEmail, complainantUserEmail, description, isApproved2, isConfirmed2);
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
