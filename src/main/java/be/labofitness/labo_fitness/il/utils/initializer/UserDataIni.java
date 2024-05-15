package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;



@Component
public class UserDataIni extends DataInitializer {


    private final UserRepository userRepository;

    public UserDataIni(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);

        if (userRepository.count() == 0) {
            User user = new User(
                    "testnom",
                    "testrepnom",
                    "aaa@aaa.aa",
                    "aaaa",
                    new Adress("aaa", "3", "city", "1212"),
                    true);

            userRepository.save(user);
        }
    }


}
