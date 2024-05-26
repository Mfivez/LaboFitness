package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;

public interface ReportService  extends CrudService<Report, Long> {

    Report makeReportWithParams(User complainant, User reportedUser, String report);

}
