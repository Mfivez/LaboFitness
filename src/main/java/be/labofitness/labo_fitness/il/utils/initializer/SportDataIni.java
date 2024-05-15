package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.ProfessionalRepository;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class SportDataIni extends DataInitializer {

    private final SportRepository sportRepository;

    public SportDataIni(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (sportRepository.count() == 0) {
            Sport a = new Sport(
            );

            //locationPlaceRepository.save(a);
        }
    }

}
