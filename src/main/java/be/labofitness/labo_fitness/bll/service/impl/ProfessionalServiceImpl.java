package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.AlreadyExistException;
import be.labofitness.labo_fitness.bll.exception.Unauthorize.UnauthorizedException;
import be.labofitness.labo_fitness.bll.model.register.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.model.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.service.*;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalAddLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalUpdateLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalAddLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageLocation.ProfessionalUpdateLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.service.service.AccreditationService;
import be.labofitness.labo_fitness.bll.service.service.ProfessionalService;
import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.*;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the {@link ProfessionalService} interface.
 * <br>Provides functionality for registering professionals
 * such as {@link Physiotherapist} and {@link Coach}.
 */
@RequiredArgsConstructor
@Service
public class ProfessionalServiceImpl implements ProfessionalService{

    private final ProfessionalRepository professionalRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final PhysiotherapistService physiotherapistService;
    private final CoachService coachService;
    private final RoleService roleService;
    private final AccreditationService accreditationService;
    private final SecurityService securityService;
    private final LocationService locationService;

    // region REGISTER

    /**
     * Registers a new {@link Professional} ({@link Physiotherapist} or {@link Coach}) based on the provided request.
     *
     * @param request the {@link Professional} registration request
     * @return a {@link RegisterResponse} indicating the success of the registration
     * @throws AlreadyExistException if the email already exists
     */
    @Override
    public RegisterResponse register(ProfessionalRegisterRequest request) {

        if(userService.checkEmail(request.email())) {
            throw new AlreadyExistException("email already exists : " + request.email());
        }

        if(request.role().equals("PHYSIOTHERAPIST")) {
            Physiotherapist physiotherapist = new Physiotherapist();
            physiotherapist.setName(request.name());
            physiotherapist.setLastname(request.lastName());
            physiotherapist.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), request.month(), request.day()));
            physiotherapist.setEmail(request.email());
            physiotherapist.setPassword(passwordEncoder.encode(request.password()));
            physiotherapist.setRoles(roleService.setRole(Set.of("USER", "PHYSIOTHERAPIST")));
            physiotherapistService.create(physiotherapist);
            accreditationService.createWithParam(request.accreditation(), request.accreditationDescription(), physiotherapist);
            }
        else if(request.role().equals("COACH"))  {
            Coach coach = new Coach();
            coach.setName(request.name());
            coach.setLastname(request.lastName());
            coach.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), request.month(), request.day()));
            coach.setEmail(request.email());
            coach.setPassword(passwordEncoder.encode(request.password()));
            coach.setRoles(roleService.setRole(Set.of("USER", "COACH")));
            coachService.create(coach);
            accreditationService.createWithParam(request.accreditation(), request.accreditationDescription(), coach);
        }

        return new RegisterResponse("Account created with success");
    }

    //endregion

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Professional} by its ID.
     *
     * @param id the ID of the {@link Professional} to retrieve
     * @return the {@link Professional} with the given ID
     */
    @Override
    public Physiotherapist getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Professional}.
     *
     * @return a list of all {@link Professional}
     */
    @Override
    public List<Professional> getAll() {
        return professionalRepository.findAll();
    }

    /**
     * Creates a new {@link Professional}.
     *
     * @param entity the {@link Professional} to create
     * @return the created {@link Professional}
     */
    @Override
    public Professional create(Professional entity) {
        return null;
    }

    /**
     * Updates an existing {@link Professional}.
     *
     * @param entity the {@link Professional} to update
     * @return the updated {@link Professional}
     */
    @Override
    public Professional update(Professional entity) {
        return null;
    }

    /**
     * Deletes an {@link Professional} by its ID.
     *
     * @param id the ID of the {@link Professional} to delete
     * @return the deleted {@link Professional}, or null if not found
     */
    @Override
    public Professional delete(Long id) {
        return null;
    }

    // endregion

    // region LOCATION PLACE

    @Override @Transactional
    public ProfessionalAddLocationPlaceResponse addLocationPlace(ProfessionalAddLocationPlaceRequest request) {
        Professional professional = securityService.getAuthentication(Professional.class);

        Address address = new Address(request.street(), request.number(), request.city(), request.zipCode());
        LocationPlace locationPlace = locationService.addLocationPlace(address);


        professional.getLocationPlace().add(locationPlace);
        professionalRepository.save(professional);

        return new ProfessionalAddLocationPlaceResponse("Location place added successfully");
    }

    @Override @Transactional
    public ProfessionalUpdateLocationPlaceResponse updateLocationPlace(ProfessionalUpdateLocationPlaceRequest request) {
        LocationPlace locationPlace = locationService.getOne(request.locationId());

        if (securityService.getAuthentication(Professional.class).getLocationPlace().stream().anyMatch(loc -> loc.equals(locationPlace))) {
            throw new UnauthorizedException("You're not allowed to update the location place" +
                    locationPlace.getAddress().getStreet() + " " +
                    locationPlace.getAddress().getNumber() + " " +
                    locationPlace.getAddress().getCity() + " " +
                    locationPlace.getAddress().getZipcode() +
                    "because this is not your professional address"
            );
        }

        locationPlace.setAddress(  new Address(  request.street(), request.number(), request.city(), request.zipCode()  )  );
        locationService.update(locationPlace);

        return new ProfessionalUpdateLocationPlaceResponse("Location place updated successfully");
    }

    // endregion

}
