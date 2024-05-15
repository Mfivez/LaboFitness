package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.dal.repository.TrainingSessionRepository;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;

import org.springframework.stereotype.Component;

@Component
public class TrainingSessionDataIni extends DataInitializer {


    private final TrainingSessionRepository trainingSessionRepository;

    public TrainingSessionDataIni(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (trainingSessionRepository.count() == 0) {
            Sport a = new Sport(
            );

            //locationPlaceRepository.save(a);
        }
    }

}
