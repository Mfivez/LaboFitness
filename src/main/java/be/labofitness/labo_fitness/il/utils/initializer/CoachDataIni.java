package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.LocationRepository;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(2)
public class CoachDataIni extends DataInitializer {

    private final RoleRepository roleRepository;
    private final CoachRepository coachRepository;
    private final LocationRepository locationRepository;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (coachRepository.count() == 0) {

            Role coachRole = roleRepository.findById(4L).orElseThrow(RuntimeException::new);
            LocationPlace cachLocationPlace = locationRepository.findById(1L).orElseThrow(RuntimeException::new);
            Set<LocationPlace> locationPlaceCoach = Set.of(cachLocationPlace);
            Set<Role> roleCoach = Set.of(coachRole);

            Coach coach1 = Coach.builder()
                    .name("Rogue")
                    .last_name("Severus")
                    .email("lepasmechant@gmail.com")
                    .password("vivevoldermort")
                    .roles(roleCoach)
                    .adress(new Adress("rue coach", "12","ville coach","zipcoach"))
                    .is_remote(false)
                    .price_hour(10)
                    .specialization("Course")
                    .build();

            Coach coach2 = Coach.builder()
                    .name("Albus")
                    .last_name("Dumbledore")
                    .email("letresgentil@gmail.com")
                    .password("mortavoldemort")
                    .roles(roleCoach)
                    .adress(new Adress("rue coach", "12","ville coach","zipcoach"))
                    .is_remote(true)
                    .price_hour(1000)
                    .specialization("Tennis")
                    .locationPlace(locationPlaceCoach)
                    .build();


            coachRepository.save(coach1);
            coachRepository.save(coach2);


        }




    }

}
