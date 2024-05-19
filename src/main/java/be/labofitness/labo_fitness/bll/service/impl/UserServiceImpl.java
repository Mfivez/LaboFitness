package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.UserLoginRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.UserGetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.UserGetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.UserGetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.UserLoginResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.UserGetPhysioResponse;
import be.labofitness.labo_fitness.bll.service.UserService;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.User;
import be.labofitness.labo_fitness.il.utils.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email()).orElseThrow(() -> new UsernameNotFoundException(loginRequest.email()));
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        String token = jwtUtil.generateToken(user);

        return UserLoginResponse.fromEntity(user, token);
    }

    // region GET PHYSIOTHERAPIST

    @Override
    public List<UserGetPhysioResponse> getAllPhysio() {
        List<Physiotherapist> physio = userRepository.findAllPhysio();
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<UserGetPhysioResponse> getPhysioBySpecialization(UserGetPhysioBySpecializationRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioBySpecialization(request.specialization());
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<UserGetPhysioResponse> getPhysioByName(UserGetPhysioByNameRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioByName(request.name());
        return physioToUserGetCoachesResponse(physio);
    }

    public List<UserGetPhysioResponse>  physioToUserGetCoachesResponse(List<Physiotherapist> physio) {
        return physio.stream()
                .map(UserGetPhysioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region GET COACHES
    @Override
    public List<UserGetCoachesResponse> getAllCoaches() {
        List<Coach> coaches = userRepository.findAllCoaches();
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<UserGetCoachesResponse> getAllCoachesByIsRemote(UserGetCoachesByRemoteRequest request) {
        List<Coach> coaches = userRepository.findCoachesByIsRemote(request.is_remote());
        return coachesToUserGetCoachesResponse(coaches);

    }

    @Override
    public List<UserGetCoachesResponse> getAllCoachesBySpecialization(UserGetCoachesBySpecializationRequest request) {
        List<Coach> coaches = userRepository.findCoachesBySpecialization(request.specialization());
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<UserGetCoachesResponse> getAllCoachesByName(UserGetCoachesByNameRequest request) {
        List<Coach> coaches = userRepository.findCoachesByName(request.name());
        return coachesToUserGetCoachesResponse(coaches);
    }

    public List<UserGetCoachesResponse>  coachesToUserGetCoachesResponse(List<Coach> coaches) {
        return coaches.stream()
                .map(UserGetCoachesResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

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
}
