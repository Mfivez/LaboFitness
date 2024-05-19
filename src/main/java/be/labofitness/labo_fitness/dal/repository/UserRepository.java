package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query( "Select u " +
            "from User u " +
            "where u.email ilike :email")
    Optional<User> findByEmail(String email);

    //region COACH
    @Query("SELECT c " +
            "FROM Coach c")
    List<Coach> findAllCoaches();

    @Query(
            "SELECT c " +
            "FROM Coach c " +
            "WHERE c.is_remote = :is_remote")
    List<Coach> findCoachesByIsRemote(boolean is_remote);

    @Query(
            "SELECT c " +
            "FROM Coach c " +
            "WHERE c.name = :coach_name")
    List<Coach> findCoachesByName(String coach_name);

    @Query(
            "SELECT c " +
                    "FROM Coach c " +
                    "WHERE c.specialization = :specialization")
    List<Coach> findCoachesBySpecialization(String specialization);
    // endregion

    //region PHYSIOTHERAPIST

    @Query("SELECT p " +
            "FROM Physiotherapist p")
    List<Physiotherapist> findAllPhysio();

    @Query(
            "SELECT p " +
                    "FROM Physiotherapist p " +
                    "WHERE p.name = :physiotherapist_name")
    List<Physiotherapist> findPhysioByName(String physiotherapist_name);

    @Query(
            "SELECT p " +
                    "FROM Physiotherapist p " +
                    "WHERE p.specialization = :specialization")
    List<Physiotherapist> findPhysioBySpecialization(String specialization);

    //endregion

}


