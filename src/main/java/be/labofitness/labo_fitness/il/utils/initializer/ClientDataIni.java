package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.*;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.enums.Goal;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
@Order(8)
public class ClientDataIni extends DataInitializer {

    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final CompetitionRepository competitionRepository;
    private final TrainingSessionRepository trainingSessionRepository;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (clientRepository.count() == 0) {

            Role client = roleRepository.findById(2L).orElseThrow(RuntimeException::new);
            Role admin = roleRepository.findById(6L).orElseThrow(RuntimeException::new);
            Role moderator = roleRepository.findById(5L).orElseThrow(RuntimeException::new);
            Competition competition = competitionRepository.findById(1L).orElseThrow(RuntimeException::new);
            //TrainingSession trainingSession = trainingSessionRepository.findById(1L).orElseThrow(RuntimeException::new);


            Client client1 = new Client();
            client1.setName("John");
            client1.setLast_name("Doe");
            client1.setEmail("john.doe@example.com");
            client1.setPassword("password");
            client1.setAdress(new Adress("123 Street",  "2", "City", "12345"));
            client1.setWeight(70);
            client1.setHeight(180);
            client1.setGoal(Goal.LEISURE_SPORT);
            client1.setLifeStyle(1.5);
            client1.setAge(30);
            client1.setRoles(Set.of(client));
            client1.setCompetitions(List.of(competition));
            //client1.setTrainingSessions(List.of(trainingSession));

            Client client2 = new Client();
            client2.setName("Jane");
            client2.setLast_name("Smith");
            client2.setEmail("jane.smith@example.com");
            client2.setPassword("password");
            client2.setAdress(new Adress("456 Avenue", "2","Town", "67890"));
            client2.setWeight(65);
            client2.setHeight(170);
            client2.setGoal(Goal.LIFESTYLE_IMPROVEMENT);
            client2.setLifeStyle(1.6);
            client2.setAge(25);
            client2.setRoles(Set.of(client, moderator));
            //client2.setTrainingSessions(List.of(trainingSession));


            clientRepository.save(client1);
            clientRepository.save(client2);
        }
    }
}