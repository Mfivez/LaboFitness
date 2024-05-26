package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(
            "SELECT r " +
            "FROM Role r " +
            "WHERE r.name = :name")
    Optional<Role> findByName(String name);

    @Query(
            "SELECT r.name " +
            "FROM Role r " )
    List<String> getAllRoleName();

}
