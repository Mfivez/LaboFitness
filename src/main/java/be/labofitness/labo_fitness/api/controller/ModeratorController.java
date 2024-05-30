package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.model.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;
import be.labofitness.labo_fitness.bll.service.service.ModeratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling moderator-related requests.
 * <p>Provides endpoints for : </p>
 * <p>1. {@code managing reports}</p>
 * <p>2. {@code updating report approval states}</p>
 */
@RestController
@RequestMapping("/api/moderator")
@RequiredArgsConstructor
public class ModeratorController {

    private final ModeratorService moderatorService;

    // region REPORT

    /**
     * Endpoint for retrieving reports with specific details.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code MODERATOR authority}.</p>
     * <p>It returns a report response based on the specified details.</p>
     *
     * @param request the {@link ReportRequest} containing report request details
     * @return a {@link ResponseEntity} containing the {@link ReportResponse}
     */
    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/report")
    public ResponseEntity<ReportResponse> getReportsWithSpecif(@Valid @ModelAttribute ReportRequest request) {
        return ResponseEntity.ok(moderatorService.moderatorGetReport(request));
    }

    /**
     * Endpoint for updating the approval state of a report.
     *
     * <p>This endpoint is accessible to authenticated users with the {@code MODERATOR authority}.</p>
     * <p>It handles requests to update the approval state of a report and returns a
     * response containing the updated approval state details.</p>
     *
     * @param request the {@link ModeratorUpdateReportIsApprovedStateRequest} containing report approval state update details
     * @return a {@link ResponseEntity} containing the {@link ModeratorReportUpdateIsApprovedStateResponse}
     */
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/report/is-approved")
    public ResponseEntity<ModeratorReportUpdateIsApprovedStateResponse> updateIsApproved(@Valid @RequestBody ModeratorUpdateReportIsApprovedStateRequest request) {
        return ResponseEntity.ok(moderatorService.moderatorUpdateIsApproved(request));
    }

    // endregion

}
