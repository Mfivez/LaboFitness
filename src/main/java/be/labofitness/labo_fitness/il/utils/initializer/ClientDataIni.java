package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.dal.repository.TrainingSessionRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.domain.enums.Goal;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Data initializer for populating the database with initial {@link Client} data.
 */
@Component
@RequiredArgsConstructor
@Order(9)
public class ClientDataIni extends DataInitializer {

    private final ClientRepository clientRepository;
    private final CompetitionRepository competitionRepository;
    private final TrainingSessionRepository trainingSessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    /**
     * Populates the database with initial {@link Client} data if no {@link Client} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (clientRepository.count() == 0) {
            Competition competition = competitionRepository.findById(1L).orElseThrow(
                    () -> new DoesntExistException("Competition Id doesn't exist: " + 1L));
            TrainingSession trainingSession = trainingSessionRepository.findById(2L).orElseThrow(
                    () -> new DoesntExistException("Training Session Id doesn't exist: " + 2L));

            Client client1 = new Client();
            client1.setName("John");
            client1.setLastname("Doe");
            client1.setEmail("john.doe@example.com");
            client1.setPassword(passwordEncoder.encode("password"));
            client1.setAddress(new Address("123 Street",  "2", "City", "12345"));
            client1.setWeight(70);
            client1.setHeight(180);
            client1.setGoal(Goal.LEISURE_SPORT);
            client1.setLifeStyle(1.5);
            client1.setRoles(roleService.setRole(Set.of("USER", "CLIENT", "MODERATOR", "ADMIN")));
            client1.setCompetitions(List.of(competition));
            client1.setTrainingSessions(List.of(trainingSession));
            client1.setBirthdate(LocalDateTime.now());

            Client client2 = new Client();
            client2.setName("Jane");
            client2.setLastname("Smith");
            client2.setEmail("jane.smith@example.com");
            client2.setPassword(passwordEncoder.encode("password"));
            client2.setAddress(new Address("456 Avenue", "2","Town", "67890"));
            client2.setWeight(65);
            client2.setHeight(170);
            client2.setGoal(Goal.LIFESTYLE_IMPROVEMENT);
            client2.setLifeStyle(1.6);
            client2.setRoles(roleService.setRole(Set.of("USER", "CLIENT")));
            client2.setTrainingSessions(List.of());
            client2.setBirthdate(LocalDateTime.now());

            clientRepository.save(client1);
            clientRepository.save(client2);
        }
    }
}