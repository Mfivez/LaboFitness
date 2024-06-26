package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Data initializer for populating the database with initial {@link Role} data.
 */
@Component
@RequiredArgsConstructor
@Order(2)
public class RoleDataIni extends DataInitializer {

    private final RoleRepository roleRepository;

    /**
     * Populates the database with initial {@link Role} data if no {@link Role} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);

        if (roleRepository.count() == 0) {
            Role user = new Role(
                    "testUser"
            );

            Role client = new Role(
                    "CLIENT",
                    "testClient"
            );

            Role physiotherapist = new Role(
                    "PHYSIOTHERAPIST",
                    "testPhysiotherapist"
            );

            Role coach = new Role(
                    "COACH",
                    "testCoach"
            );

            Role moderator = new Role(
                    "MODERATOR",
                    "testModerator"
            );

            Role admin = new Role(
                    "ADMIN",
                    "testAdmin"
            );

            roleRepository.save(user);
            roleRepository.save(client);
            roleRepository.save(physiotherapist);
            roleRepository.save(coach);
            roleRepository.save(moderator);
            roleRepository.save(admin);
        }
    }


}
