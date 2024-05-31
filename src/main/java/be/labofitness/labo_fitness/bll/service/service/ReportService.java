package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Service interface for managing {@link Report}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface ReportService  extends CrudService<Report, Long> {

    /**
     * Creates a {@link Report} with the provided parameters.
     *
     * @param complainant   the {@link User} who is making the {@link Report}
     * @param reportedUser  the {@link User} who is being reported
     * @param reportMessage the message describing the {@link Report}
     */
    void makeReportWithParams(User complainant, User reportedUser, String reportMessage);

    /**
     * Retrieves a list of {@link Report} entities based on the provided specifications.
     *
     * @param specification the specification to filter the reports
     * @return a list of {@link Report} entities that match the provided specifications
     */
    List<Report> getReportsBySpecification(Specification<Report> specification);

}
