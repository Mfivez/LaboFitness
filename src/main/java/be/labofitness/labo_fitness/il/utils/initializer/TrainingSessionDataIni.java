package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.dal.repository.TrainingSessionRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(11)
public class TrainingSessionDataIni extends DataInitializer {


    private final TrainingSessionRepository trainingSessionRepository;
    private final ClientRepository clientRepository;
    private final CoachRepository coachRepository;


    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (trainingSessionRepository.count() == 0) {
            /**
            //Client client = clientRepository.findById(1L).orElseThrow();
            Coach coach = coachRepository.findById(4L).orElseThrow();

            TrainingSession session1 = new TrainingSession();
            session1.setName("Session 1");
            session1.setDuration(60);
            session1.setStart_date(LocalDateTime.now());
            session1.setEnd_date(LocalDateTime.now().plusHours(1));
            session1.setDescription("This is session 1");
            session1.setRecommended_level(RecommendedLevel.AMATEUR);
            //session1.setClientSubscriber(List.of(client));
            session1.setCoach(coach);

            TrainingSession session2 = new TrainingSession();
            session2.setName("Session 2");
            session2.setDuration(90);
            session2.setStart_date(LocalDateTime.now().plusDays(1));
            session2.setEnd_date(LocalDateTime.now().plusDays(1).plusHours(1));
            session2.setDescription("This is session 2");
            session2.setRecommended_level(RecommendedLevel.GIGACHAD);
            //session2.setClientSubscriber(List.of(client)); // NON
            session2.setCoach(coach); // NULLABLE AND CREATE AN ATTRIBUTE CREATE IF THE SESSION IS JUST A FILE

            trainingSessionRepository.save(session1);
            trainingSessionRepository.save(session2);
             */
        }
    }

}
