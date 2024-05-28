package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.dal.repository.PostRepository;
import be.labofitness.labo_fitness.dal.repository.SportRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Post;
import be.labofitness.labo_fitness.domain.entity.Sport;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Data initializer for populating the database with initial {@link Post} data.
 */
@Component
@RequiredArgsConstructor
@Order(13)
public class PostDataIni extends DataInitializer {

    private final PostRepository postRepository;
    private final ClientRepository clientRepository;
    private final SportRepository sportRepository;

    /**
     * Populates the database with initial {@link Post} data if no {@link Post} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (postRepository.count() == 0) {
            Client creator = clientRepository.findById(5L).orElseThrow();
            Sport sport = sportRepository.findById(1L).orElseThrow();

            Post post = new Post();
            post.setName("Post 1");
            post.setDescription("Post 1 description");
            post.setSport(sport);
            post.setCreator(creator);

            postRepository.save(post);
        }
    }
}
