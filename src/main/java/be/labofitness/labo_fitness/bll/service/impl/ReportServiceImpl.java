package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.service.service.ReportService;
import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override @Transactional
    public Report makeReportWithParams(User complainant, User reportedUser, String reportMessage) {
        Report report = new Report();
        report.setComplainant(complainant);
        report.setReportedUser(reportedUser);
        report.setDescription(reportMessage);
        report.setDate(LocalDateTime.now());

        return reportRepository.save(report);
    }

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Report} by its ID.
     *
     * @param id the ID of the {@link Report} to retrieve
     * @return the {@link Report} with the given ID
     */
    @Override
    public Report getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Report}.
     *
     * @return a list of all {@link Report}
     */
    @Override
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    /**
     * Creates a new {@link Report}.
     *
     * @param entity the {@link Report} to create
     * @return the created {@link Report}
     */
    @Override
    public Report create(Report entity) {
        return null;
    }

    /**
     * Updates an existing {@link Report}.
     *
     * @param entity the {@link Report} to update
     * @return the updated {@link Report}
     */
    @Override
    public Report update(Report entity) {
        return null;
    }

    /**
     * Deletes an {@link Report} by its ID.
     *
     * @param id the ID of the {@link Report} to delete
     * @return the deleted {@link Report}, or null if not found
     */
    @Override
    public Report delete(Long id) {
        return null;
    }

    // endregion

}

