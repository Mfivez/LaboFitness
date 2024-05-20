package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.ClientAlreadyExistsException;
import be.labofitness.labo_fitness.bll.exception.alreadyExists.ProfessionalAlreadyExistsException;
import be.labofitness.labo_fitness.bll.models.request.professionnel.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.models.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.AccreditationService;
import be.labofitness.labo_fitness.bll.service.ProfessionalService;
import be.labofitness.labo_fitness.dal.repository.*;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfessionalServiceImpl implements ProfessionalService{

    //BUG GITHUB
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PhysiotherapistRepository physiotherapistRepository;
    private final CoachRepository coachRepository;
    private final RoleRepository roleRepository;
    private final AccreditationService accreditationService;


    @Override
    public RegisterResponse register(ProfessionalRegisterRequest request) {
        //TODO POURQUOI PAS BOOLEAN !!
        //TODO questions pour seb : pourquoi le boolean / pourquoi le authenticate ne fonctionne pas
        if(userRepository.existsByEmail(request.email()) > 0) {
            throw new ProfessionalAlreadyExistsException("email already exists : " + request.email());
        }


        if(request.role().equals("PHYSIOTHERAPIST")) {
            Physiotherapist physiotherapist = new Physiotherapist();
            physiotherapist.setName(request.name());
            physiotherapist.setLast_name(request.lastName());
            physiotherapist.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), request.month(), request.day()));
            physiotherapist.setEmail(request.email());
            physiotherapist.setPassword(passwordEncoder.encode(request.password()));
            physiotherapist.setRoles(LaboFitnessUtil.setRole(Set.of("PHYSIOTHERAPIST"),roleRepository));
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
            coach.setRoles(LaboFitnessUtil.setRole(Set.of("COACH"),roleRepository));
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
