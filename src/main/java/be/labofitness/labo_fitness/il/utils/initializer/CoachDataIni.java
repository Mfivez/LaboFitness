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

            Coach coach1 = new Coach();
            coach1.setName("John");
            coach1.setLast_name("Doe");
            coach1.setEmail("john.doe@example.com");
            coach1.setPassword("password");
            coach1.setAdress(new Adress("123 Street", "2", "City", "12345"));
            coach1.setSpecialization("Fitness");
            coach1.setPrice_hour(50);

            Coach coach2 = new Coach();
            coach2.setName("Jane");
            coach2.setLast_name("Smith");
            coach2.setEmail("jane.smith@example.com");
            coach2.setPassword("password");
            coach2.setAdress(new Adress("456 Avenue", "2","Town", "67890"));
            coach2.setSpecialization("Yoga");
            coach2.set_remote(true);
            coach2.setPrice_hour(60);


            coachRepository.save(coach1);
            coachRepository.save(coach2);
        }
    }
}