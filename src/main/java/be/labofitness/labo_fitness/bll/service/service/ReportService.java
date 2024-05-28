package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;

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

}
