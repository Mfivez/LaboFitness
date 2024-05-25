package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.exception.notMatching.PasswordNotMatchingException;
import be.labofitness.labo_fitness.bll.models.request.client.manageAccount.changePassword.ClientChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.changePassword.CoachChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.changePassword.CoachChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.CoachService;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodeName;

@Service
public class CoachServiceImpl implements CoachService {
    private final UserRepository userRepository;
    private final CoachRepository coachRepository;
    private final PasswordEncoder passwordEncoder;

    public CoachServiceImpl(UserRepository userRepository, CoachRepository coachRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.coachRepository = coachRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public CoachManageAccountResponse manageAccount(CoachManageAccountRequest request) {

        String message  = getCurrentMethodeName();

        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Coach coach = LaboFitnessUtil.getAuthentication(Coach.class);
        coach.setName(request.name());
        coach.setLast_name(request.lastName());
        coach.setEmail(request.email());
        coach.setGender(request.gender());
        coach.setAdress(new Adress(request.street(), request.number(), request.city(), request.zipCode()));
        coach.setRemote(request.isRemote());
        coach.setPriceHour(request.pricePerHour());

        coachRepository.save(coach);
        return CoachManageAccountResponse.fromEntity(coach,message);
    }

    @Override
    @Transactional
    public CoachChangePasswordResponse changePassword(CoachChangePasswordRequest request) {

        String message = getCurrentMethodeName();

        Coach coach = LaboFitnessUtil.getAuthentication(Coach.class);

        if(!passwordEncoder.encode(request.oldPassword()).equals(coachRepository.findPasswordByCoachId(coach.getId()))){

            throw new PasswordNotMatchingException("passwords are not matching");
        }
        coach.setPassword(passwordEncoder.encode(request.newPassword()));
        coachRepository.save(coach);

        return CoachChangePasswordResponse.fromEntity(coach, message);
    }


    @Override
    public Coach getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Coach> getAll() {
        return List.of();
    }

    @Override
    public Coach create(Coach entity) {
        return null;
    }

    @Override
    public Coach update(Coach entity) {
        return null;
    }

    @Override
    public Coach delete(Long aLong) {
        return null;
    }
}
