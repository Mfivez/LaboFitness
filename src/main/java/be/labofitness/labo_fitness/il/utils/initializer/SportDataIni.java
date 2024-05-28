package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.domain.enums.TypeSport;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Data initializer for populating the database with initial {@link Sport} data.
 */
@Component
@RequiredArgsConstructor
@Order(1)
public class SportDataIni extends DataInitializer {

    private final SportRepository sportRepository;

    /**
     * Populates the database with initial {@link Sport} data if no {@link Sport} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (sportRepository.count() == 0) {
            Sport sport1 = new Sport();
            sport1.setName("Football");
            sport1.setDescription("A team sport played with a spherical ball between two teams of 11 players.");
            sport1.setTypeSport(TypeSport.TEAM_SPORT);

            Sport sport2 = new Sport();
            sport2.setName("Tennis");
            sport2.setDescription("A racket sport that can be played individually against a single opponent (singles) or between two teams of two players each (doubles).");
            sport2.setTypeSport(TypeSport.INDIVIDUAL_SPORT);

            Sport sport3 = new Sport();
            sport3.setName("Swimming");
            sport3.setDescription("The sport of propelling oneself through water using the limbs.");
            sport3.setTypeSport(TypeSport.INDIVIDUAL_SPORT);

            sportRepository.save(sport1);
            sportRepository.save(sport2);
            sportRepository.save(sport3);
        }
    }
}