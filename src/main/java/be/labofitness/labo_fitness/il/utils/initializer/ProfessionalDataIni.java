package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.LocationRepository;
import be.labofitness.labo_fitness.dal.repository.ProfessionalRepository;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalDataIni extends DataInitializer {


    private final ProfessionalRepository professionalRepository;

    public ProfessionalDataIni(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (professionalRepository.count() == 0) {
            Professional a = new Professional(
            );

            //locationPlaceRepository.save(a);
        }
    }

}
