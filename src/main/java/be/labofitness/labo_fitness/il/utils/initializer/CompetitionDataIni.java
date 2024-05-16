package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CompetitionDataIni extends DataInitializer {


    private final CompetitionRepository competitionRepository;

    public CompetitionDataIni(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (competitionRepository.count() == 0) {
            Competition competition1 = new Competition();
            competition1.setName("Marathon");
            competition1.setStartDate(LocalDateTime.now());
            competition1.setEndDate(LocalDateTime.now().plusDays(7));

            competitionRepository.save(competition1);
        }
    }
}
