package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(11)
public class ReportDataIni extends DataInitializer {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public ReportDataIni(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (reportRepository.count() == 0) {
            User reportedUser = userRepository.findById(5L).orElseThrow();
            User complainant = userRepository.findById(1L).orElseThrow();

            Report report = new Report();
            report.setDescription("John a fait quelque chose de mal");
            report.setDate(LocalDateTime.now());
            report.setComplainant(complainant);
            report.setReportedUser(reportedUser);
            report.setConfirmed(true);

            reportRepository.save(report);
        }
    }
}
