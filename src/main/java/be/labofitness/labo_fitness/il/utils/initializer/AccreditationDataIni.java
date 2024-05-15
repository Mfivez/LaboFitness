package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

@Component
public class AccreditationDataIni extends DataInitializer {


    private final AccreditationRepository accreditationRepository;

    public AccreditationDataIni(AccreditationRepository accreditationRepository) {
        this.accreditationRepository = accreditationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);

        /**
        if (accreditationRepository.count() == 0) {



             *             Accreditation a = Accreditation.builder()
             *                     .type(AccreditationType.Master)
             *                     .description("Ceci est une description")
             *                     .professional()
             *                     .build();
             *
             *             //accreditationRepository.save(a);
             *         }
             */

    }

}
