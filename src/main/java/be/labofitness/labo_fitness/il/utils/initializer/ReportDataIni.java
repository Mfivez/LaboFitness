package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.ProfessionalRepository;
import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

@Component
public class ReportDataIni extends DataInitializer {


    private final ReportRepository reportRepository;

    public ReportDataIni(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (reportRepository.count() == 0) {
            Report a = new Report(
            );

            //locationPlaceRepository.save(a);
        }
    }

}