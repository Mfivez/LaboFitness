package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.model.request.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.response.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.PhysiotherapistService;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodeName;

@Service
@RequiredArgsConstructor
public class PhysiotherapistServiceImpl implements PhysiotherapistService {

    private final PlanningService planningService;
    private final UserRepository userRepository;
    private final PhysiotherapistRepository physiotherapistRepository;
    private final PasswordEncoder  passwordEncoder;

    // region GET PLANNING

    @Override
    public PlanningResponse getPlanning(PhysioPlanningRequest request) {
        return new PlanningResponse(
                planningService.getAllPhysioAppointments(request).stream().map(Appointment::getStartDate).collect(Collectors.toList()),
                planningService.getAllPhysioAppointments(request).stream().map(Appointment::getEndDate).collect(Collectors.toList()),
                planningService.getAllPhysioAppointments(request).stream().map(Appointment::getName).collect(Collectors.toList()));
    }

    //endregion

    // region CLASSIC CRUD
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
    // endregion

    // region MANAGE ACCOUNT

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

    // endregion
}
