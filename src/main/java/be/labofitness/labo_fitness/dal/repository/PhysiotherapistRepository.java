package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long> {
    @Query(
            "SELECT p " +
            "FROM Physiotherapist p " +
            "WHERE p.email = :email")
    Optional<Physiotherapist> findByEmail(String email);

    @Query("SELECT p.password FROM Physiotherapist p WHERE p.id = :id")
    String findPasswordByPhysiotherapistId(Long id);
}
