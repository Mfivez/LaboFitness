package be.labofitness.labo_fitness.dal.repository;
import be.labofitness.labo_fitness.dal.util.HasFindByMethod;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for managing {@link User} entities.
 * <br>Extends {@link JpaRepository} to inherit basic CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, HasFindByMethod<User> {

    // region UTILS METHODS

    /**
     * Retrieves a {@link User} by their email address, ignoring case.
     *
     * @param email The email address of the {@link User}.
     * @return An optional containing the {@link User} if found, or empty otherwise.
     */
    @Query( "Select u " +
            "from User u " +
            "where u.email ilike :email")
    Optional<User> findByEmail(String email);

    //endregion

    //region REGISTER

    /**
     * Checks if a {@link User} with the given email address exists.
     *
     * @param email The email address to check.
     * @return True if a {@link User} with the email address exists, false otherwise.
     */
    @Query(
            "SELECT COUNT(*)>0 " +
            "FROM User  " +
            "WHERE email ILIKE :email" )
    boolean existsByEmail(String email);

    //endregion

    // region REPORT

    /**
     * Retrieves the description of {@link Report} by their validation status for a given user.
     *
     * @param userReportedId The ID of the reported user.
     * @param isValidate     The validation status of the {@link Report}.
     * @return A set of {@link Report} descriptions that match the criteria.
     */
    @Query(
            "SELECT r.description " +
            "FROM Report r " +
            "JOIN r.reportedUser u " +
            "WHERE u.id = :userReportedId " +
            "AND r.isConfirmed = :isValidate ")
    Set<String> getReportMessageByIsValidate(Long userReportedId, boolean isValidate);

    //endregion

}


