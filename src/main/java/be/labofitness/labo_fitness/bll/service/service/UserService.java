package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.login.UserLoginRequest;
import be.labofitness.labo_fitness.bll.model.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.model.user.getReport.GetReportResponse;
import be.labofitness.labo_fitness.bll.model.login.UserLoginResponse;
import be.labofitness.labo_fitness.bll.model.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.entity.Report;
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

    // region UTIL FUNCTION

    boolean checkEmail(String email);

    User getOneByEmail(String email);

    // endregion

}
