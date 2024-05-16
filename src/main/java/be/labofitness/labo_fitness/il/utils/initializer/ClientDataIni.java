package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.enums.Goal;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@RequiredArgsConstructor
@Order(2)
public class ClientDataIni extends DataInitializer {

    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (clientRepository.count() == 0) {

            Role client = roleRepository.findById(2L).orElseThrow(RuntimeException::new);
            Role admin = roleRepository.findById(6L).orElseThrow(RuntimeException::new);
            Role moderator = roleRepository.findById(5L).orElseThrow(RuntimeException::new);
            Set<Role> rolesclient = Set.of(client);
            Set<Role> rolesmodo = Set.of(client, moderator);
            Goal goalUser = Goal.COMPETITION;

            Client client1 = Client.builder()
                            .name("James")
                            .last_name("Potter")
                            .age(15)
                            .adress(new Adress("rue", "ville","12","234"))
                            .password("AAAAAAAAA")
                            .email("James")
                            .goal(goalUser)
                            .roles(rolesclient)
                            .height(145)
                            .weight(77)
                            .lifeStyle(2.2)
                            .build();

            Client client2 = Client.builder()
                    .name("Henri")
                    .last_name("Jacques")
                    .age(15)
                    .adress(new Adress("rue2", "ville2","122","11"))
                    .password("BBBBBBB")
                    .email("henri@gmail.com")
                    .goal(goalUser)
                    .roles(rolesmodo)
                    .height(185)
                    .weight(65)
                    .lifeStyle(2.2)
                    .build();

            clientRepository.save(client2);
            clientRepository.save(client1);


        }





    }


}
