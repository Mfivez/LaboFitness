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

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    // region CLASSIC CRUD
    @Override @Transactional
    public Report makeReportWithParams(User complainant, User reportedUser, String reportMessage) {
        Report report = new Report();
        report.setComplainant(complainant);
        report.setReportedUser(reportedUser);
        report.setDescription(reportMessage);
        report.setDate(LocalDateTime.now());

        return reportRepository.save(report);
    }

    @Override
    public Report delete(Long aLong) {
        return null;
    }

    @Override
    public Report update(Report entity) {
        return null;
    }

    @Override
    public Report create(Report entity) {
        return null;
    }

    @Override
    public List<Report> getAll() {
        return List.of();
    }

    @Override
    public Report getOne(Long aLong) {
        return null;
    }

    // endregion
}
