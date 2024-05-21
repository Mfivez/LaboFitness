package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(5)
public class PhysiotherapistDataIni extends DataInitializer {


    private final PhysiotherapistRepository physiotherapistRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (physiotherapistRepository.count() == 0) {

            Physiotherapist physiotherapist1 = new Physiotherapist();
            physiotherapist1.setName("John");
            physiotherapist1.setLast_name("Doe");
            physiotherapist1.setEmail("john@example.com");
            physiotherapist1.setPassword("password");
            physiotherapist1.setAdress(new Adress("123 Street", "2", "City", "12345"));
            physiotherapist1.setSpecialization("Kine");
            physiotherapist1.setInami_number(1234568888);
            physiotherapist1.setRoles((LaboFitnessUtil.setRole(Set.of("USER", "PHYSIOTHERAPIST"), roleRepository)));
            physiotherapist1.setWorkSchedule("Du Lundi au Vendredi 9h - 17h");
            physiotherapist1.setBirthdate(LocalDateTime.now());

            Physiotherapist physiotherapist2 = new Physiotherapist();
            physiotherapist2.setName("Jane");
            physiotherapist2.setLast_name("Smith");
            physiotherapist2.setEmail("jane@example.com");
            physiotherapist2.setPassword("password");
            physiotherapist2.setAdress(new Adress("123 Street", "2", "City", "12345"));
            physiotherapist2.setSpecialization("Kine");
            physiotherapist2.setInami_number(1234578888);
            physiotherapist2.setRoles((LaboFitnessUtil.setRole(Set.of("USER", "PHYSIOTHERAPIST"), roleRepository)));
            physiotherapist2.setWorkSchedule("Du Lundi au Vendredi 9h - 17h");
            physiotherapist2.setBirthdate(LocalDateTime.now());

            physiotherapistRepository.save(physiotherapist1);
            physiotherapistRepository.save(physiotherapist2);

        }
    }
}
