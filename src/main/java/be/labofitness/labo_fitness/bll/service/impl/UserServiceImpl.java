package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.AlreadyExistException;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.exception.notMatching.NotMatchingException;
import be.labofitness.labo_fitness.bll.model.login.UserLoginRequest;
import be.labofitness.labo_fitness.bll.model.login.UserLoginResponse;
import be.labofitness.labo_fitness.bll.model.user.changePassword.ChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.user.changePassword.ChangePasswordResponse;
import be.labofitness.labo_fitness.bll.model.user.getReport.GetReportResponse;
import be.labofitness.labo_fitness.bll.model.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.model.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.service.CoachService;
import be.labofitness.labo_fitness.bll.service.service.PhysiotherapistService;
import be.labofitness.labo_fitness.bll.service.service.ReportService;
import be.labofitness.labo_fitness.bll.service.service.UserService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.domain.enums.Gender;
import be.labofitness.labo_fitness.il.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import static be.labofitness.labo_fitness.il.utils.Anonymizer.genereteRandomString;
import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodName;

/**
 * Implementation of the {@link UserService} interface.
 * <br>Provides methods to manage {@link User}.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReportService reportService;
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;
    private final PhysiotherapistService physiotherapistService;
    private final CoachService coachService;
    private final JwtUtil jwtUtil;

    // region UTILS FUNCTIONS

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Loads a {@link User} by their email.
     *
     * @param email the email of the {@link User} to load
     * @return the {@link UserDetails} of the {@link User}
     * @throws UsernameNotFoundException if the {@link User} is not found
     * @throws AlreadyExistException if the {@code mail} already exists
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public boolean emailUpdateIfValid(String email, String newEmail){
        boolean isValid = false;
        if(!email.equals(newEmail)){
            if(!userRepository.existsByEmail(newEmail)){  isValid =true;  }
            else{  throw new AlreadyExistException("email already exists: " + newEmail); }
        }
        return isValid;
    }

    // endregion

    // region LOGIN

    /**
     * Authenticates a {@link User} and generates a {@code JWT token}.
     *
     * @param loginRequest the login request containing the email and password
     * @return the {@link UserLoginResponse} containing the {@link User} details and {@code JWT token}
     * @throws UsernameNotFoundException if the {@link User} is not found
     * @throws BadCredentialsException if the password is incorrect
     */
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

    //region CHANGE PASSWORD

    @Override @Transactional
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        User user = securityService.getAuthentication(User.class);

        if(!passwordEncoder.matches(request.oldPassword(),user.getPassword())){

            throw new NotMatchingException("wrong password");
        }
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        return ChangePasswordResponse.fromEntity(user, getCurrentMethodName());
    }

    //endregion

    // region REPORT

    /**
     * Creates a new {@link Report} based on the given request.
     *
     * @param request the request containing the details of the {@link Report}
     * @return the {@link ReportResponse} containing the result of the {@link Report} creation
     * @throws DoesntExistException if the reported {@link User}'s email does not exist
     */
    @Override @Transactional
    public ReportResponse makeReport(MakeReportRequest request) {
        User complainant = securityService.getAuthentication(User.class);

        User reportedUser =  userRepository.findByEmail(request.reportedUserEmail())
                .orElseThrow(() -> new DoesntExistException( "Email doesn't exist: " + request.reportedUserEmail() ));

        reportService.makeReportWithParams(complainant, reportedUser, request.report());

        return new ReportResponse("Vous avez bien reporté l'utilisateur " +
                reportedUser.getName() + " " + reportedUser.getLastname() +
                " pour le motif suivant : " + request.report());
    }

    /**
     * Retrieves valid {@link Report} for the authenticated {@link User}.
     *
     * @return a set of {@link GetReportResponse} containing the valid reports
     */
    @Override
    public Set<GetReportResponse> getValidReport() {
        return userRepository.getReportMessageByIsValidate(
                securityService.getAuthentication(User.class).getId(),true).stream()
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
        return userRepository.findById(id).orElseThrow(() -> new DoesntExistException("user id : "+ id + " doesn't exists"));
    }

    /**
     * Retrieves an {@link User} by its email.
     *
     * @param email the email of the {@link User} to retrieve
     * @return the {@link User} with the given email
     */
    @Override
    public User getOneByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new DoesntExistException("User doesn't exist"));
    }

    /**
     * Retrieves all {@link User}.

     * @return a list of all {@link User}
     */
    @Override
    public List<User> getAll() { return userRepository.findAll(); }

    /**
     * Creates a new {@link User}.
     *
     * @param entity the {@link User} to create
     * @return the created {@link User}
     */
    @Override
    public User create(User entity) { return null; }

    /**
     * Updates an existing {@link User}.
     *
     * @param entity the {@link User} to update
     * @return the updated {@link User}
     */
    @Override
    public User update(User entity) { return null; }

    @Override
    public User updateAccountStatus(User user, boolean status) {
        user.setActive(status);
        return userRepository.save(user);
    }

    @Override
    public User updateEmailStatus(User user, boolean status){
        user.setMailActive(status);
        return userRepository.save(user);
    }

    @Override
    public User anonymizeUser(User user){

        if(user.getRoles().stream().anyMatch(role -> role.getName().equals("PHYSIOTHERAPIST"))){
            Physiotherapist physio = physiotherapistService.getOneByEmail(user.getEmail());
            physio.setInamiNumber(physio.getId().intValue());
            physiotherapistService.update(physio);
        }

        if(user.getRoles().stream().anyMatch( role -> role.getName().equals("COACH"))){
            Coach coach = coachService.getOneByEmail(user.getEmail());
            coach.setRemote(false);
            coach.setPriceHour(0);
            coachService.update(coach);
        }

        user.setName(genereteRandomString(user.getName()));
        user.setLastname(genereteRandomString(user.getLastname()));
        user.setEmail(UUID.randomUUID().toString());
        user.setMailActive(false);
        user.setActive(false);
        user.setBirthdate(LocalDateTime.now());
        user.setGender(Gender.UNDEFINED);

        return userRepository.save(user);
    }

    /**
     * Deletes an {@link User} by its ID.
     *
     * @param id the ID of the {@link User} to delete
     * @return the deleted {@link User}, or null if not found
     */
    @Override
    public User delete(Long id) { return null; }

    // endregion

}
