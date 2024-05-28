package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.model.request.professionnel.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.model.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.service.AccreditationService;
import be.labofitness.labo_fitness.bll.service.service.ProfessionalService;
import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.dal.repository.*;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfessionalServiceImpl implements ProfessionalService{


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;  //TODO REFAC
    private final PhysiotherapistRepository physiotherapistRepository;  //TODO REFAC
    private final CoachRepository coachRepository; //TODO REFAC
    private final RoleRepository roleRepository;  //TODO REFAC
    private final AccreditationService accreditationService;
    private final RoleService roleService;


    @Override
    public RegisterResponse register(ProfessionalRegisterRequest request) {

        if(userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("email already exists : " + request.email());
        }


        if(request.role().equals("PHYSIOTHERAPIST")) {
            Physiotherapist physiotherapist = new Physiotherapist();
            physiotherapist.setName(request.name());
            physiotherapist.setLast_name(request.lastName());
            physiotherapist.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), request.month(), request.day()));
            physiotherapist.setEmail(request.email());
            physiotherapist.setPassword(passwordEncoder.encode(request.password()));
            physiotherapist.setRoles(roleService.setRole(Set.of("USER", "PHYSIOTHERAPIST"),roleRepository));
            physiotherapistRepository.save(physiotherapist);
            accreditationService.createWithParam(request.accreditation(), request.accreditationDescription(), physiotherapist);
            }
        else if(request.role().equals("COACH"))  {
            Coach coach = new Coach();
            coach.setName(request.name());
            coach.setLast_name(request.lastName());
            coach.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), request.month(), request.day()));
            coach.setEmail(request.email());
            coach.setPassword(passwordEncoder.encode(request.password()));
            coach.setRoles(roleService.setRole(Set.of("USER", "COACH"),roleRepository));
            coachRepository.save(coach);
            accreditationService.createWithParam(request.accreditation(), request.accreditationDescription(), coach);
        }



        return new RegisterResponse("Account created with success");
    }

    @Override
    public Professional getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Professional> getAll() {
        return List.of();
    }

    @Override
    public Professional create(Professional entity) {
        return null;
    }

    @Override
    public Professional update(Professional entity) {
        return null;
    }

    @Override
    public Professional delete(Long aLong) {
        return null;
    }
}
