package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.LocationRepository;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(4)
public class CoachDataIni extends DataInitializer {

    private final RoleRepository roleRepository;
    private final CoachRepository coachRepository;
    private final LocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (coachRepository.count() == 0) {
            LocationPlace coachLocationPlace = locationRepository.findById(1L).orElseThrow(RuntimeException::new);


            Coach coach1 = new Coach();
            coach1.setName("Jeremy");
            coach1.setLastname("Doe");
            coach1.setEmail("jerem.doe@example.com");
            coach1.setPassword(passwordEncoder.encode("password"));
            coach1.setAddress(new Address("123 Street", "2", "City", "12345"));
            coach1.setSpecialization("Fitness");
            coach1.setPriceHour(50);
            coach1.setRoles((roleService.setRole(Set.of("USER", "COACH"), roleRepository)));
            coach1.setLocationPlace(Set.of(coachLocationPlace));
            coach1.setWorkSchedule("Du Lundi au Vendredi 9h - 17h");
            coach1.setBirthdate(LocalDateTime.now());

            Coach coach2 = new Coach();
            coach2.setName("Yanis");
            coach2.setLastname("Smith");
            coach2.setEmail("Yanis.smith@example.com");
            coach2.setPassword(passwordEncoder.encode("password"));
            coach2.setAddress(new Address("456 Avenue", "2","Town", "67890"));
            coach2.setSpecialization("Yoga");
            coach2.setRemote(true);
            coach2.setPriceHour(60);
            coach2.setRoles((roleService.setRole(Set.of("USER", "COACH"), roleRepository)));
            coach2.setLocationPlace(Set.of(coachLocationPlace));
            coach2.setWorkSchedule("Du Lundi au Vendredi 9h - 17h");
            coach2.setBirthdate(LaboFitnessUtil.createNewDate(2000, Month.JANUARY, 12));

            coachRepository.save(coach1);
            coachRepository.save(coach2);
        }
    }
}