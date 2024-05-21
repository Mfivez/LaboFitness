package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.doesntExists.EmailDoesntExistException;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.UserLoginRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.UserLoginResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.ReportService;
import be.labofitness.labo_fitness.bll.service.UserService;
import be.labofitness.labo_fitness.dal.repository.ReportRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.il.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ReportService reportService;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

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

    // region MAKE REPORT

    @Override @Transactional
    public ReportResponse makeReport(Authentication authentication, MakeReportRequest request) {
        User complainant = (User) authentication.getPrincipal();

        User reportedUser =  userRepository.findByEmail(request.reportedUserEmail())
                .orElseThrow(() -> new EmailDoesntExistException(
                        "L'email " + request.reportedUserEmail() + " n'existe pas"));

        reportService.makeReportWithParams(complainant, reportedUser, request.report());


        return new ReportResponse("Vous avez bien reporté l'utilisateur " +
                reportedUser.getName() + " " + reportedUser.getLast_name() +
                " pour le motif suivant : " + request.report());
    }

    // endregion

    // region GET PHYSIOTHERAPIST

    @Override
    public List<GetPhysioResponse> getAllPhysio() {
        List<Physiotherapist> physio = userRepository.findAllPhysio();
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPhysioBySpecialization(GetPhysioBySpecializationRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioBySpecialization(request.specialization());
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPhysioByName(GetPhysioByNameRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioByName(request.name());
        return physioToUserGetCoachesResponse(physio);
    }

    public List<GetPhysioResponse>  physioToUserGetCoachesResponse(List<Physiotherapist> physio) {
        return physio.stream()
                .map(GetPhysioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region GET COACHES
    @Override
    public List<GetCoachesResponse> getAllCoaches() {
        List<Coach> coaches = userRepository.findAllCoaches();
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllCoachesByIsRemote(GetCoachesByRemoteRequest request) {
        List<Coach> coaches = userRepository.findCoachesByIsRemote(request.is_remote());
        return coachesToUserGetCoachesResponse(coaches);

    }

    @Override
    public List<GetCoachesResponse> getAllCoachesBySpecialization(GetCoachesBySpecializationRequest request) {
        List<Coach> coaches = userRepository.findCoachesBySpecialization(request.specialization());
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllCoachesByName(GetCoachesByNameRequest request) {
        List<Coach> coaches = userRepository.findCoachesByName(request.name());
        return coachesToUserGetCoachesResponse(coaches);
    }

    public List<GetCoachesResponse>  coachesToUserGetCoachesResponse(List<Coach> coaches) {
        return coaches.stream()
                .map(GetCoachesResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region TRAINING SESSIONS

    @Override
    public List<GetTrainingSessionResponse> findAllTrainingSession() {
        List<TrainingSession> trainingSessions = userRepository.findAllTrainingSessions();
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByRecommendedLevel(request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByDuration(request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByName(GetTrainingSessionsByNameRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByName(request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByCoachName(request.coach_name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    public List<GetTrainingSessionResponse>  trainingSessionToClientGetTrainingSessionResponse(List<TrainingSession> trainings) {
        return trainings.stream()
                .map(GetTrainingSessionResponse::fromEntity)
                .collect(Collectors.toList());
    }
    // endregion

    // region CLASSIC CRUD
    @Override
    public User getOne(Long aLong) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User delete(Long aLong) {
        return null;
    }

    // endregion

}
