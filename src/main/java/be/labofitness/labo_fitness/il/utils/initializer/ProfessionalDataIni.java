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
            Professional professional1 = new Professional();
            professional1.setName("John");
            professional1.setLast_name("Doe");
            professional1.setEmail("john@example.com");
            professional1.setPassword("password");
            professional1.setSpecialization("Physiotherapy");

            Professional professional2 = new Professional();
            professional2.setName("Jane");
            professional2.setLast_name("Smith");
            professional2.setEmail("jane@example.com");
            professional2.setPassword("password");
            professional2.setSpecialization("Chiropractic");

            professionalRepository.save(professional1);
            professionalRepository.save(professional2);
        }
    }
}
