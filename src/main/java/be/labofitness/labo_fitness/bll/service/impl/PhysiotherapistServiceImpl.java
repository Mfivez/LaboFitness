package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.exception.notMatching.PasswordNotMatchingException;
import be.labofitness.labo_fitness.bll.models.request.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.physiotherapist.manageAccount.changePassWord.PhysiotherapistChangePasswordRequest;
import be.labofitness.labo_fitness.bll.models.response.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.physiotherapist.manageAccount.changePassword.PhysiotherapistChangePasswordResponse;
import be.labofitness.labo_fitness.bll.service.PhysiotherapistService;
import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodeName;

@Service
public class PhysiotherapistServiceImpl implements PhysiotherapistService{

    private final UserRepository userRepository;
    private final PhysiotherapistRepository physiotherapistRepository;
    private final PasswordEncoder  passwordEncoder;

    public PhysiotherapistServiceImpl(UserRepository userRepository, PhysiotherapistRepository physiotherapistRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.physiotherapistRepository = physiotherapistRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    @Transactional
    public PhysiotherapistManageAccountResponse manageAccount(PhysiotherapistManageAccountRequest request) {

        String message = getCurrentMethodeName();

        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Physiotherapist physiotherapist = LaboFitnessUtil.getAuthentication(Physiotherapist.class);
        physiotherapist.setName(request.name());
        physiotherapist.setLast_name(request.lastName());
        physiotherapist.setEmail(request.email());
        physiotherapist.setGender(request.gender());
        physiotherapist.setAdress(new Adress(request.street(), request.number(), request.city(), request.zipCode()));
        physiotherapist.setInami_number(request.inamiNumber());

        physiotherapistRepository.save(physiotherapist);

        return PhysiotherapistManageAccountResponse.fromEntity(physiotherapist,message);
    }

    @Override
    @Transactional
    public PhysiotherapistChangePasswordResponse changePassword(PhysiotherapistChangePasswordRequest request) {

        String message = getCurrentMethodeName();

        Physiotherapist physiotherapist = LaboFitnessUtil.getAuthentication(Physiotherapist.class);

        if(!passwordEncoder.encode(request.oldPassword()).equals(physiotherapistRepository.findPasswordByPhysiotherapistId(physiotherapist.getId()))){

            throw new PasswordNotMatchingException("passwords are not matching");
        }

        physiotherapist.setPassword(passwordEncoder.encode(request.newPassword()));
        physiotherapistRepository.save(physiotherapist);

        return PhysiotherapistChangePasswordResponse.fromEntity(physiotherapist,message);
    }


    @Override
    public Physiotherapist getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Physiotherapist> getAll() {
        return List.of();
    }

    @Override
    public Physiotherapist create(Physiotherapist entity) {
        return null;
    }

    @Override
    public Physiotherapist update(Physiotherapist entity) {
        return null;
    }

    @Override
    public Physiotherapist delete(Long aLong) {
        return null;
    }

}
