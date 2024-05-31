package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.service.service.ReportService;
import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the {@link ReportService} interface.
 * <br>Provides methods to manage {@link Report} entities.
 */
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    //region MAKE REPORT

    /**
     * Creates a new {@link Report} with the specified parameters.
     *
     * @param complainant the user making the {@link Report}
     * @param reportedUser the user being reported
     * @param reportMessage the message describing the {@link Report}
     */
    @Override @Transactional
    public void makeReportWithParams(User complainant, User reportedUser, String reportMessage) {
        Report report = new Report();
        report.setComplainant(complainant);
        report.setReportedUser(reportedUser);
        report.setDescription(reportMessage);
        report.setDate(LocalDateTime.now());

        reportRepository.save(report);
    }

    // endregion

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Report} by its ID.
     *
     * @param id the ID of the {@link Report} to retrieve
     * @return the {@link Report} with the given ID
     */
    @Override
    public Report getOne(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new DoesntExistException("reportId doesn't exist:" + id));
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
        return reportRepository.save(entity);
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

    // region SPECIFICATION

    /**
     * Retrieves a list of reports based on the provided specification.
     *
     * @param specification the specification to filter reports
     * @return a list of reports that match the provided specification
     */
    public List<Report> getReportsBySpecification(Specification<Report> specification) {
        return reportRepository.findAll(specification);
    }

    //endregion
}

