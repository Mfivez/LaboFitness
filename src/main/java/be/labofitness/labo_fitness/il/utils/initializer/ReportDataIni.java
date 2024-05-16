package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.ProfessionalRepository;
import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
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
            // Recherche de deux utilisateurs pour le reporting
            User reportedUser = userRepository.findById(1L).orElseThrow();
            User complainant = userRepository.findById(1L).orElseThrow();

            // Création d'un rapport
            Report report = new Report();
            report.setDescription("John a fait quelque chose de mal");
            report.setDate(LocalDateTime.now());

            // Enregistrement du rapport
            reportRepository.save(report);
        }
    }
}
