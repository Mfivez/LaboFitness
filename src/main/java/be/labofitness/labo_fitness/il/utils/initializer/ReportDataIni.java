package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
            List<User> users = userRepository.findAll();
            if (users.size() < 2) {
                throw new RuntimeException("Not enough users in the database to create reports.");
            }

            User complainant1 = users.get(0);
            User complainant2 = users.get(1);

            List<String> descriptions = Arrays.asList(
                    "John a fait quelque chose de mal 1",
                    "Jane a enfreint les règles 2",
                    "Peter a causé des problèmes 3",
                    "Lucy a perturbé la séance 4",
                    "Mark a été en retard 5",
                    "Anna a manqué de respect 6",
                    "Tom a brisé du matériel 7",
                    "Sara a été impolie 8",
                    "Jim a dérangé les autres 9",
                    "Linda a enfreint les consignes 10"
            );

            for (int i = 0; i < 10; i++) {
                User reportedUser = users.get((i % (users.size() - 1)) + 1);
                Report report = new Report();
                report.setDescription(descriptions.get(i));
                report.setDate(LocalDateTime.now().minusDays(i));
                report.setComplainant(i % 2 == 0 ? complainant1 : complainant2);
                report.setReportedUser(reportedUser);
                report.setConfirmed(i % 2 == 0);
                report.setApproved(i % 3 == 0);

                reportRepository.save(report);
            }
        }
    }
}