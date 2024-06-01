package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.login.UserLoginRequest;
import be.labofitness.labo_fitness.bll.model.login.UserLoginResponse;
import be.labofitness.labo_fitness.bll.model.user.changePassword.ChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.user.changePassword.ChangePasswordResponse;
import be.labofitness.labo_fitness.bll.model.user.getReport.GetReportResponse;
import be.labofitness.labo_fitness.bll.model.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.model.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

/**
 * Service interface for managing {@link User}.
 * <ol>
 *     <li>Extends {@link CrudService} for basic CRUD operations </li>
 *     <li>{@link UserDetailsService} for user authentication.</li>
 * </ol>
 */
public interface UserService extends CrudService<User, Long> , UserDetailsService {

    //region LOGIN

    /**
     * Authenticates a {@link User} based on the provided login request.
     *
     * @param loginRequest the login request containing {@link User} credentials
     * @return the response containing user details and {@code authentication token}
     */
    UserLoginResponse login(UserLoginRequest loginRequest);

    //endregion

    //region CHANGE PASSWORD

    ChangePasswordResponse changePassword(ChangePasswordRequest request);

    //endregion

    // region REPORT

    /**
     * Makes a {@link Report} for a {@link User} based on the provided {@link Report} request.
     *
     * @param request the {@link Report} request containing {@link Report} details
     * @return the response confirming the successful {@link Report} submission
     */
    ReportResponse makeReport(MakeReportRequest request);

    /**
     * Retrieves valid {@link Report} for the authenticated {@link User}.
     *
     * @return a set of valid {@link Report} responses
     */
    Set<GetReportResponse> getValidReport();

    // endregion

    //region UTILS

    /**
     * Updates the email address of a {@link User} if the new email address is valid.
     *
     * @param email    the current email address of the user
     * @param newEmail the new email address to be set
     * @return {@code true} if the email update was successful, {@code false} otherwise
     */
    boolean emailUpdateIfValid(String email, String newEmail);

    /**
     * Retrieves a {@link User} entity by its email address.
     *
     * @param email the email address of the user
     * @return the {@link User} entity with the specified email address
     */
    User getOneByEmail(String email);

    /**
     * Updates the account status of a {@link User}.
     *
     * @param user   the user whose account status is to be updated
     * @param status the new account status
     * @return the updated {@link User} entity
     */
    User updateAccountStatus(User user, boolean status);

    /**
     * Updates the email status of a {@link User}.
     *
     * @param user   the user whose email status is to be updated
     * @param status the new email status
     * @return the updated {@link User} entity
     */
    User updateEmailStatus(User user, boolean status);

    /**
     * Anonymizes a {@link User}.
     *
     * @param user the user to be anonymized
     * @return the anonymized {@link User} entity
     */
    User anonymizeUser(User user);

    /**
     * Checks if an email address is valid.
     *
     * @param email the email address to be checked
     * @return {@code true} if the email address is valid, {@code false} otherwise
     */
    boolean checkEmail(String email);

    //endregion

}
