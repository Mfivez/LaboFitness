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

            Role client = roleRepository.findById(4L).orElseThrow(RuntimeException::new);
            Role admin = roleRepository.findById(6L).orElseThrow(RuntimeException::new);
            Set<Role> roles = Set.of(client);
            Goal goalUser = Goal.COMPETITION;

            Client client1 = Client.builder()
                            .name("James")
                            .last_name("Potter")
                            .age(15)
                            .adress(new Adress("rue", "ville","12","234"))
                            .password("AAAAAAAAA")
                            .email("James")
                            .goal(goalUser)
                            .roles(roles)
                            .height(145)
                            .weight(77)
                            .lifeStyle(2.2)
                            .build();



            clientRepository.save(client1);

            System.out.println(client1);

        }





    }


}
