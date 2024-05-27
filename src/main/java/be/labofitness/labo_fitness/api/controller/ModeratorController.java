package be.labofitness.labo_fitness.api.controller;

import be.labofitness.labo_fitness.bll.model.request.moderator.report.ReportRequest;
import be.labofitness.labo_fitness.bll.model.request.moderator.report.ModeratorUpdateReportIsApprovedStateRequest;
import be.labofitness.labo_fitness.bll.model.response.moderator.report.ReportResponse;
import be.labofitness.labo_fitness.bll.model.response.moderator.report.ModeratorReportUpdateIsApprovedStateResponse;
import be.labofitness.labo_fitness.bll.service.service.ModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/moderator")
@RequiredArgsConstructor
public class ModeratorController {

    private final ModeratorService moderatorService;

    // region REPORT

    @GetMapping("/report")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ResponseEntity<ReportResponse> getReportsWithSpecif(@ModelAttribute ReportRequest request) {
        return ResponseEntity.ok(moderatorService.moderatorGetReport(request));
    }

    @PostMapping("/report/is-approved")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ResponseEntity<ModeratorReportUpdateIsApprovedStateResponse> updateIsApproved(@RequestBody ModeratorUpdateReportIsApprovedStateRequest request) {
        return ResponseEntity.ok(moderatorService.moderatorUpdateIsApproved(request));
    }

    // endregion


}
