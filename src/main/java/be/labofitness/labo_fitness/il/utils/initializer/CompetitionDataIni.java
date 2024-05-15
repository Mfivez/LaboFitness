package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.Builder;
import org.springframework.stereotype.Component;

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
            Competition a = new Competition(
            );

            //competitionRepository.save(a);
        }
    }

}
