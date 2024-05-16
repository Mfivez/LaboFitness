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
            Physiotherapist a = new Physiotherapist(
            );

            //physiotherapistRepository.save(a);
            //TODO
        }
    }

}
