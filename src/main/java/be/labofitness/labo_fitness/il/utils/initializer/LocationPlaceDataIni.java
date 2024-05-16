package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.dal.repository.LocationRepository;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class LocationPlaceDataIni extends DataInitializer {


    private final LocationRepository locationPlaceRepository;

    public LocationPlaceDataIni(LocationRepository locationPlaceRepository) {
        this.locationPlaceRepository = locationPlaceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (locationPlaceRepository.count() == 0) {
            LocationPlace locationPlacetest = LocationPlace.builder()
                    .adress(new Adress("RueLocationPlace1","12A", "LocationPlaceCity", "AAAEA"))
                    .build();

            locationPlaceRepository.save(locationPlacetest);

        }
    }

}
