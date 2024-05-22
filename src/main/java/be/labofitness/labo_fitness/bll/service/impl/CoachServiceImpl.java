package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.service.CoachService;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    private final UserRepository userRepository;
    private final CoachRepository coachRepository;

    public CoachServiceImpl(UserRepository userRepository, CoachRepository coachRepository) {
        this.userRepository = userRepository;
        this.coachRepository = coachRepository;
    }

    @Override
    public CoachManageAccountResponse manageAccount(CoachManageAccountRequest request) {
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
        return new CoachManageAccountResponse("Account modified with success");
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
