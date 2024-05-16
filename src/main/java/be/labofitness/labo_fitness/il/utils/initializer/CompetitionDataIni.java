package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(7)
public class CompetitionDataIni extends DataInitializer {


    private final CompetitionRepository competitionRepository;
    private final CoachRepository coachRepository;
    private final SportRepository sportRepository;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (competitionRepository.count() == 0) {
            Coach coach = coachRepository.findById(4L).orElseThrow();
            Sport sport = sportRepository.findById(1L).orElseThrow();
            Sport sport2 = sportRepository.findById(2L).orElseThrow();

            Competition competition1 = new Competition();
            competition1.setName("Marathon");
            competition1.setStartDate(LocalDateTime.now());
            competition1.setEndDate(LocalDateTime.now().plusDays(7));
            competition1.setCoach(coach);
            competition1.setSports(Set.of(sport, sport2));

            competitionRepository.save(competition1);

        }
    }
}
