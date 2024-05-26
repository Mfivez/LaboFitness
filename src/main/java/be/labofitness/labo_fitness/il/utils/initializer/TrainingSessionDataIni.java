package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.TrainingSessionRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Order(8)
public class TrainingSessionDataIni extends DataInitializer {


    private final TrainingSessionRepository trainingSessionRepository;
    private final CoachRepository coachRepository;


    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (trainingSessionRepository.count() == 0) {

            Coach coach = coachRepository.findById(1L).orElseThrow();

            TrainingSession session1 = new TrainingSession();
            session1.setName("Session 1");
            session1.setStartDate(LocalDateTime.now());
            session1.setEndDate(LocalDateTime.now().plusHours(1));
            session1.setDescription("This is session 1");
            session1.setRecommended_level(RecommendedLevel.AMATEUR);
            session1.setCoach(coach);

            TrainingSession session2 = new TrainingSession();
            session2.setName("Session 2");
            session2.setStartDate(LocalDateTime.now().plusDays(1));
            session2.setEndDate(LocalDateTime.now().plusDays(1).plusHours(1));
            session2.setDescription("This is session 2");
            session2.setRecommended_level(RecommendedLevel.GIGACHAD);
            session2.setCoach(coach); // NULLABLE AND CREATE AN ATTRIBUTE CREATE IF THE SESSION IS JUST A FILE

            trainingSessionRepository.save(session1);
            trainingSessionRepository.save(session2);

        }
    }

}
