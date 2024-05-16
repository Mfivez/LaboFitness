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
        if (accreditationRepository.count() == 0) {
            Accreditation accreditation1 = new Accreditation();
            accreditation1.setType(AccreditationType.LICENCE);
            accreditation1.setDescription("Certification in fitness training");

            Accreditation accreditation2 = new Accreditation();
            accreditation2.setType(AccreditationType.MASTER);
            accreditation2.setDescription("Diploma in nutrition");

            accreditationRepository.save(accreditation1);
            accreditationRepository.save(accreditation2);
        }
    }
}
