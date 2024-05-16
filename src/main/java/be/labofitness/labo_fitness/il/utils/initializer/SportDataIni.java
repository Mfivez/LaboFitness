package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.ProfessionalRepository;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.domain.enums.TypeSport;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.Builder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SportDataIni extends DataInitializer {

    private final SportRepository sportRepository;

    public SportDataIni(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (sportRepository.count() == 0) {
            Sport sport1 = new Sport();
            sport1.setName("Football");
            sport1.setDescription("A team sport played with a spherical ball between two teams of 11 players.");
            sport1.setTypeSport(TypeSport.SPORTEQUIPE);

            Sport sport2 = new Sport();
            sport2.setName("Tennis");
            sport2.setDescription("A racket sport that can be played individually against a single opponent (singles) or between two teams of two players each (doubles).");
            sport2.setTypeSport(TypeSport.SPORTSOLO);

            Sport sport3 = new Sport();
            sport3.setName("Swimming");
            sport3.setDescription("The sport of propelling oneself through water using the limbs.");
            sport3.setTypeSport(TypeSport.SPORTSOLO);

            sportRepository.save(sport1);
            sportRepository.save(sport2);
            sportRepository.save(sport3);
        }
    }
}