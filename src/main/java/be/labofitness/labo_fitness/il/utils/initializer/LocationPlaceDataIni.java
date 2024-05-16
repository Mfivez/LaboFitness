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
            Adress adress1 = new Adress("123 Street", "B", "City", "12345");
            LocationPlace locationPlace1 = new LocationPlace(adress1);

            Adress adress2 = new Adress("456 Avenue", "B",  "Town", "67890");
            LocationPlace locationPlace2 = new LocationPlace(adress2);


            locationPlaceRepository.save(locationPlace1);
            locationPlaceRepository.save(locationPlace2);
        }
    }
}
