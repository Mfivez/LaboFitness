package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.dal.util.HasFindByMethod;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Repository interface for managing {@link Physiotherapist} entities.</p>
 * <p>Extends {@link JpaRepository} to inherit basic CRUD operations.</p>
 */
@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long>, HasFindByMethod<Physiotherapist>, JpaSpecificationExecutor<Physiotherapist> {

    /**
     * Finds a {@link Physiotherapist} by email.
     *
     * @param email The email of the {@link Physiotherapist}.
     * @return An {@link Optional} {@link Physiotherapist} object.
     */
    @Query(
            "SELECT p " +
            "FROM Physiotherapist p " +
            "WHERE p.email = :email")
    Optional<Physiotherapist> findByEmail(String email);

}
