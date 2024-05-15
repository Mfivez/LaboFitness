package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CoachDataIni extends DataInitializer {

    private final RoleRepository roleRepository;
    private final CoachRepository coachRepository;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (coachRepository.count() == 0) {

            Role coachRole = roleRepository.findById(4L).orElseThrow(RuntimeException::new);

            Coach coach1 = Coach.builder()
                    .name("Rogue")
                    .last_name("Severus")
                    .email("lepasmechant@gmail.com")
                    .password("")


                    .build();



            //coachRepository.save(coach);


        }




    }

}
