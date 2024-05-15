package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

@Component
public class RoleDataIni extends DataInitializer {


    private final RoleRepository roleRepository;

    public RoleDataIni(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (roleRepository.count() == 0) {
            Role user = new Role(
                    //"user",
                    "testUser"
            );

            Role client = new Role(
                    "client",
                    "testClient"
            );

            Role physiotherapist = new Role(
                    "physiotherapist",
                    "testPhysiotherapist"
            );

            Role coach = new Role(
                    "coach",
                    "testCoach"
            );

            Role moderator = new Role(
                    "moderator",
                    "testModerator"
            );

            Role admin = new Role(
                    "admin",
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
