package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * <p>Repository interface for managing {@link Competition} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 * <p>Extends {@link JpaSpecificationExecutor} to support dynamic query execution.</p>
 */
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long>, JpaSpecificationExecutor<Competition> {


    // region COMPETITION

    /**
     * Finds a {@link Competition} by its name identifier.
     *
     * @param competitionNameId The name identifier of the {@link Competition}.
     * @return An {@link Optional} {@link Competition} object.
     */
    @Query(
            "SELECT c " +
            "FROM Competition c " +
            "WHERE c.competitionNameIdentifier = :competitionNameId")
    Optional<Competition> findByCompetitionNameId(String competitionNameId);

    /**
     * Finds a {@link Competition} by its ID.
     *
     * @param id The ID of the {@link Competition}.
     * @return An {@link Optional} {@link Competition} object.
     */
    @Query(
            "SELECT c " +
            "FROM Competition c " +
            "WHERE c.id = :id")
    Optional<Competition> findByCompetitionId(Long id);

    // endregion

}
