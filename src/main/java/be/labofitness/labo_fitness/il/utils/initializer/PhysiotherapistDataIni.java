package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

@Component
public class PhysiotherapistDataIni extends DataInitializer {


    private final PhysiotherapistRepository physiotherapistRepository;

    public PhysiotherapistDataIni(PhysiotherapistRepository physiotherapistRepository) {
        this.physiotherapistRepository = physiotherapistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (physiotherapistRepository.count() == 0) {
            Physiotherapist physiotherapist1 = new Physiotherapist();
            physiotherapist1.setName("John");
            physiotherapist1.setLast_name("Doe");
            physiotherapist1.setEmail("john@example.com");
            physiotherapist1.setPassword("password");
            physiotherapist1.setInami_number(123456);

            Physiotherapist physiotherapist2 = new Physiotherapist();
            physiotherapist2.setName("Jane");
            physiotherapist2.setLast_name("Smith");
            physiotherapist2.setEmail("jane@example.com");
            physiotherapist2.setPassword("password");
            physiotherapist1.setInami_number(123457);

            physiotherapistRepository.save(physiotherapist1);
            physiotherapistRepository.save(physiotherapist2);
        }
    }
}
