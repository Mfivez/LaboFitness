package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Data initializer for populating the database with initial {@link Competition} data.
 */
@Component
@RequiredArgsConstructor
@Order(7)
public class CompetitionDataIni extends DataInitializer {

    private final CompetitionRepository competitionRepository;
    private final CoachRepository coachRepository;
    private final SportRepository sportRepository;

    /**
     * Populates the database with initial {@link Competition} data if no {@link Competition} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (competitionRepository.count() == 0) {
            Coach coach = coachRepository.findById(1L).orElseThrow();
            Sport sport = sportRepository.findById(1L).orElseThrow();
            Sport sport2 = sportRepository.findById(2L).orElseThrow();
            String name = "Marathon";
            LocalDateTime startDate = LocalDateTime.of(2025, 12, 10, 10, 30);
            LocalDateTime endDate = LocalDateTime.of(2025, 12, 10, 17, 0);

            Competition competition1 = new Competition();
            competition1.setName(name);
            competition1.setStartDate(startDate);
            competition1.setEndDate(endDate);
            competition1.setCoach(coach);
            competition1.setSports(Set.of(sport, sport2));
            competition1.setInscriptionOpen(true);
            competition1.setCompetitionNameIdentifier(LaboFitnessUtil.CompetitionNameIdBuilder(name, startDate));

            competitionRepository.save(competition1);
        }
    }
}
