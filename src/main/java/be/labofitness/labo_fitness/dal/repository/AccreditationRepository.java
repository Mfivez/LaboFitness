package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Repository interface for managing {@link Accreditation} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 */
@Repository
public interface AccreditationRepository extends JpaRepository<Accreditation, Long> {
}
