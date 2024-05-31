package be.labofitness.labo_fitness.api.controller;
import be.labofitness.labo_fitness.bll.model.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.model.user.getReport.GetReportResponse;
import be.labofitness.labo_fitness.bll.model.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

/**
 * Controller for handling user-related requests.
 * Provides endpoints for
 * <p>1. {@code making reports}</p>
 * <p>2. {@code retrieving reports}</p>
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // region REPORT

    /**
     * Endpoint for making a report.
     *
     * <p>This endpoint is accessible to authenticated users.</p>
     *
     * @param request the {@link MakeReportRequest} containing report details
     * @return a {@link ResponseEntity} containing a {@link ReportResponse}
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/make-report")
    public ResponseEntity<ReportResponse> makeReport(@Valid @RequestBody MakeReportRequest request) {
        return ResponseEntity.ok(userService.makeReport(request));
    }

    /**
     * Endpoint for retrieving reports.
     *
     * <p>This endpoint is accessible to authenticated users.</p>
     *
     * @return a {@link ResponseEntity} containing a set of {@link GetReportResponse}
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get-report")
    public ResponseEntity<Set<GetReportResponse>> getReportResponseByIsValidate() {
        return ResponseEntity.ok(userService.getValidReport());
    }

    // endregion


}
