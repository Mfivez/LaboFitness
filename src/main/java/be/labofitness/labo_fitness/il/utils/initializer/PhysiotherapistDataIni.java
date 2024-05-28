package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(5)
public class PhysiotherapistDataIni extends DataInitializer {


    private final PhysiotherapistRepository physiotherapistRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (physiotherapistRepository.count() == 0) {

            Physiotherapist physiotherapist1 = new Physiotherapist();
            physiotherapist1.setName("John");
            physiotherapist1.setLastname("Doe");
            physiotherapist1.setEmail("john@example.com");
            physiotherapist1.setPassword(passwordEncoder.encode("password"));
            physiotherapist1.setAddress(new Address("123 Street", "2", "City", "12345"));
            physiotherapist1.setSpecialization("Kine");
            physiotherapist1.setInamiNumber(1234568888);
            physiotherapist1.setRoles((roleService.setRole(Set.of("USER", "PHYSIOTHERAPIST"), roleRepository)));
            physiotherapist1.setWorkSchedule("Du Lundi au Vendredi 9h - 17h");
            physiotherapist1.setBirthdate(LocalDateTime.now());

            Physiotherapist physiotherapist2 = new Physiotherapist();
            physiotherapist2.setName("Jane");
            physiotherapist2.setLastname("Smith");
            physiotherapist2.setEmail("jane@example.com");
            physiotherapist2.setPassword(passwordEncoder.encode("password"));
            physiotherapist2.setAddress(new Address("123 Street", "2", "City", "12345"));
            physiotherapist2.setSpecialization("Kine");
            physiotherapist2.setInamiNumber(1234578888);
            physiotherapist2.setRoles((roleService.setRole(Set.of("USER", "PHYSIOTHERAPIST"), roleRepository)));
            physiotherapist2.setWorkSchedule("Du Lundi au Vendredi 9h - 17h");
            physiotherapist2.setBirthdate(LocalDateTime.now());

            physiotherapistRepository.save(physiotherapist1);
            physiotherapistRepository.save(physiotherapist2);

        }
    }
}
