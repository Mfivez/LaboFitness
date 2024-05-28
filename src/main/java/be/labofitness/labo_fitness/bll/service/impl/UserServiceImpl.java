package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.doesntExists.EmailDoesntExistException;
import be.labofitness.labo_fitness.bll.model.login.UserLoginRequest;
import be.labofitness.labo_fitness.bll.model.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.model.user.getReport.GetReportResponse;
import be.labofitness.labo_fitness.bll.model.login.UserLoginResponse;
import be.labofitness.labo_fitness.bll.model.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.service.ReportService;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.il.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ReportService reportService;
    private final SecurityService securityService;

    // region UTILS FUNCTIONS
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
    // endregion

    // region LOGIN

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email()).orElseThrow(() -> new UsernameNotFoundException(loginRequest.email()));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        String token = jwtUtil.generateToken(user);

        return UserLoginResponse.fromEntity(user, token);
    }

    // endregion

    // region REPORT

    @Override @Transactional
    public ReportResponse makeReport(MakeReportRequest request) {
        User complainant = securityService.getAuthentication(User.class);

        User reportedUser =  userRepository.findByEmail(request.reportedUserEmail())
                .orElseThrow(() -> new EmailDoesntExistException(
                        "L'email " + request.reportedUserEmail() + " n'existe pas"));

        reportService.makeReportWithParams(complainant, reportedUser, request.report());


        return new ReportResponse("Vous avez bien reporté l'utilisateur " +
                reportedUser.getName() + " " + reportedUser.getLastname() +
                " pour le motif suivant : " + request.report());
    }

    @Override
    public Set<GetReportResponse> getValidReport() {
        User user = securityService.getAuthentication(User.class);
        return userRepository.getReportMessageByIsValidate(user.getId(),true).stream()
                .map(GetReportResponse::new)
                .collect(Collectors.toSet());
    }


    // endregion

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link User} by its ID.
     *
     * @param id the ID of the {@link User} to retrieve
     * @return the {@link User} with the given ID
     */
    @Override
    public User getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link User}.

     * @return a list of all {@link User}
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Creates a new {@link User}.
     *
     * @param entity the {@link User} to create
     * @return the created {@link User}
     */
    @Override
    public User create(User entity) {
        return null;
    }

    /**
     * Updates an existing {@link User}.
     *
     * @param entity the {@link User} to update
     * @return the updated {@link User}
     */
    @Override
    public User update(User entity) {
        return null;
    }

    /**
     * Deletes an {@link User} by its ID.
     *
     * @param id the ID of the {@link User} to delete
     * @return the deleted {@link User}, or null if not found
     */
    @Override
    public User delete(Long id) {
        return null;
    }

    // endregion

}
