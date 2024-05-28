package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Repository interface for managing {@link LocationPlace} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 */
@Repository
public interface LocationRepository extends JpaRepository<LocationPlace, Long> {
}
