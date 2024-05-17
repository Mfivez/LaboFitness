package be.labofitness.labo_fitness.dal.repository;

import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query( "Select u " +
            "from User u " +
            "where u.email ilike :email")
    Optional<User> findByEmail(String email);
}
