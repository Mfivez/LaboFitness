package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.exception.notMatching.NotMatchingException;
import be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.PhysiotherapistService;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodName;

/**
 * Implementation of the {@link PhysiotherapistService} interface.
 * <br>Provides methods for managing {@link Physiotherapist}-related operations.
 */
@RequiredArgsConstructor
@Service
public class PhysiotherapistServiceImpl implements PhysiotherapistService {

    private final PlanningService planningService;
    private final SecurityService securityService;
    private final UserRepository userRepository; //TODO REFAC
    private final PhysiotherapistRepository physiotherapistRepository;

    // region GET PLANNING

    /**
     * Retrieves the planning for a {@link Physiotherapist} based on the provided request.
     *
     * @param request the request containing criteria for fetching the planning
     * @return a {@link PlanningResponse} object containing the planning details
     */
    @Override
    public List<PlanningResponse> getPlanning(PhysioPlanningRequest request) {
        return IntStream.range(0, planningService.getAllPhysioAppointments(request).stream()
                        .map(Appointment::getStartDate).toList().size())
                .mapToObj(i -> new PlanningResponse(
                        planningService.getAllPhysioAppointments(request).stream()
                                .map(Appointment::getStartDate).toList().get(i),
                        planningService.getAllPhysioAppointments(request).stream()
                                .map(Appointment::getEndDate).toList().get(i),
                        planningService.getAllPhysioAppointments(request).stream()
                                .map(Appointment::getName).toList().get(i)))
                .collect(Collectors.toList());
    }

    //endregion

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Physiotherapist} by its ID.
     *
     * @param id the ID of the {@link Physiotherapist} to retrieve
     * @return the {@link Physiotherapist} with the given ID
     */
    @Override
    public Physiotherapist getOne(Long id) {return null;}

    /**
     * Retrieves an {@link Physiotherapist} by its email.
     *
     * @param email the email of the {@link Physiotherapist} to retrieve
     * @return the {@link Physiotherapist} with the given email
     */
    @Override
    public Physiotherapist getOneByEmail(String email) {
        return physiotherapistRepository.findByEmail(email).orElseThrow(
                () -> new DoesntExistException("Email doesn't exist: " + email));
    }

    /**
     * Retrieves all {@link Physiotherapist}.
     *
     * @return a list of all {@link Physiotherapist}
     */
    @Override
    public List<Physiotherapist> getAll() {return physiotherapistRepository.findAll();}

    /**
     * Creates a new {@link Physiotherapist}.
     *
     * @param entity the {@link Physiotherapist} to create
     * @return the created {@link Physiotherapist}
     */
    @Override
    public Physiotherapist create(Physiotherapist entity) {
        return physiotherapistRepository.save(entity);
    }

    /**
     * Updates an existing {@link Physiotherapist}.
     *
     * @param entity the {@link Physiotherapist} to update
     * @return the updated {@link Physiotherapist}
     */
    @Override
    public Physiotherapist update(Physiotherapist entity) {return null;}

    /**
     * Deletes an {@link Physiotherapist} by its ID.
     *
     * @param id the ID of the {@link Physiotherapist} to delete
     * @return the deleted {@link Physiotherapist}, or null if not found
     */
    @Override
    public Physiotherapist delete(Long id) {return null;}

    // endregion

    // region MANAGE ACCOUNT


    /**
     * Update an {@link Physiotherapist} account
     * @param request of the {@link PhysiotherapistManageAccountRequest} to update
     * @return response {@link PhysiotherapistManageAccountResponse} with a message
     */
    @Override
    @Transactional
    public PhysiotherapistManageAccountResponse manageAccount(PhysiotherapistManageAccountRequest request) {

        Physiotherapist physiotherapist = securityService.getAuthentication(Physiotherapist.class);

        if (!physiotherapist.getEmail().equals(request.email())) {
            if (!userRepository.existsByEmail(request.email())) {  physiotherapist.setEmail(request.email());  }
            else{
                throw new NotMatchingException("Email already exists");
            }
        }

        physiotherapist.setName(request.name());
        physiotherapist.setLastname(request.lastName());
        physiotherapist.setGender(request.gender());
        physiotherapist.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
        physiotherapist.setInamiNumber(request.inamiNumber());

        physiotherapistRepository.save(physiotherapist);

        return PhysiotherapistManageAccountResponse.fromEntity(physiotherapist, getCurrentMethodName());
    }

    // endregion

    // region SPECIFICATION

    @Override
    public List<Physiotherapist> getPhysiotherapistBySpecification(Specification<Physiotherapist> specification) {
        return physiotherapistRepository.findAll(specification);
    }

    // endregion

}
