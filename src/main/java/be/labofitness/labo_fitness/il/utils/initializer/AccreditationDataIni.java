package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.ProfessionalRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Data initializer for populating the database with initial {@link Accreditation} data.
 */
@Component
@RequiredArgsConstructor
@Order(6)
public class AccreditationDataIni extends DataInitializer {

    private final AccreditationRepository accreditationRepository;
    private final ProfessionalRepository professionalRepository;

    /**
     * Populates the database with initial {@link Accreditation} data if no {@link Accreditation} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (accreditationRepository.count() == 0) {
            Professional professional = professionalRepository.findById(3L).orElseThrow();

            Accreditation accreditation1 = new Accreditation();
            accreditation1.setType(AccreditationType.LICENCE);
            accreditation1.setDescription("Certification in fitness training");
            accreditation1.setProfessional(professional);

            Accreditation accreditation2 = new Accreditation();
            accreditation2.setType(AccreditationType.MASTER);
            accreditation2.setDescription("Diploma in nutrition");
            accreditation2.setProfessional(professional);

            accreditationRepository.save(accreditation1);
            accreditationRepository.save(accreditation2);
        }
    }

}
