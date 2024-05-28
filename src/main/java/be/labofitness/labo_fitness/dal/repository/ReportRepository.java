package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Repository interface for managing {@link Report} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 * <p>Extends {@link JpaSpecificationExecutor} to support dynamic query execution.</p>
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {

    /**
     * Retrieves a {@link Report} by its ID.
     *
     * @param id The ID of the {@link Report} to retrieve.
     * @return An {@link Optional} containing the {@link Report}, or empty if not found.
     */
    @Query(
            "SELECT r " +
            "FROM Report r " +
            "WHERE r.id = :id")
    Optional<Report> findById(long id);

}
