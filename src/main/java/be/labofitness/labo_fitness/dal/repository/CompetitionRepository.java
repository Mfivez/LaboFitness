package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long>, JpaSpecificationExecutor<Competition> {


    // region COMPETITION

    @Query(
            "SELECT c " +
            "FROM Competition c " +
            "WHERE c.competitionNameIdentifier = :competitionNameId")
    Optional<Competition> findByCompetitionNameId(String competitionNameId);

    @Query(
            "SELECT c " +
            "FROM Competition c " +
            "WHERE c.id = :id")
    Optional<Competition> findByCompetitionId(Long id);

    // endregion

}
