package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Repository interface for managing {@link Post} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 */
public interface PostRepository extends JpaRepository<Post, Long> {

}
