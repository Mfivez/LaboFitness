package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Repository interface for managing {@link Role} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a {@link Role} by its name.
     *
     * @param name The name of the {@link Role} to retrieve.
     * @return An {@link Optional} containing the {@link Role}, or empty if not found.
     */
    @Query(
            "SELECT r " +
            "FROM Role r " +
            "WHERE r.name = :name")
    Optional<Role> findByName(String name);

}
