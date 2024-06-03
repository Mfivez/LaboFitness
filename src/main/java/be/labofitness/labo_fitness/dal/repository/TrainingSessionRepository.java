package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>Repository interface for managing {@link TrainingSession} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 * <p>Extends {@link JpaSpecificationExecutor} to support dynamic query execution.</p>
 */
@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long>, JpaSpecificationExecutor<TrainingSession> {
}
