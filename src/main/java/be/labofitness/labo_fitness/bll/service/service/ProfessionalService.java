package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.addAccredition.ProfessionalAddAccreditationRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.addAccredition.ProfessionalAddAccreditationResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.updateAccreditation.ProfessionalUpdateAccreditationRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageAccreditation.updateAccreditation.ProfessionalUpdateAccreditationResponse;
import be.labofitness.labo_fitness.bll.model.register.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.model.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.addLocationPlace.ProfessionalAddLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.updateLocationPlace.ProfessionalUpdateLocationPlaceRequest;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.addLocationPlace.ProfessionalAddLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.model.professionnel.manageAccount.manageLocation.updateLocationPlace.ProfessionalUpdateLocationPlaceResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Professional;

/**
 * Service interface for managing {@link Professional}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface ProfessionalService extends CrudService<Professional, Long> {

    /**
     * Registers a new {@link Professional} based on the provided request.
     *
     * @param request the registration request containing {@link Professional} details
     * @return the response indicating the registration result
     */
    RegisterResponse register(ProfessionalRegisterRequest request);

    // region LOCATION PLACE

    ProfessionalAddLocationPlaceResponse addLocationPlace(ProfessionalAddLocationPlaceRequest request);

    ProfessionalUpdateLocationPlaceResponse updateLocationPlace(ProfessionalUpdateLocationPlaceRequest request);

    /**
     * Registers a new {@link Accreditation} for a  {@link Professional}) based on the provided request.
     *
     * @param request the {@link Professional} registration request
     * @return a {@link ProfessionalAddAccreditationResponse} indicating the success of the registration
     */
    ProfessionalAddAccreditationResponse addAccreditation(ProfessionalAddAccreditationRequest request);

    /**
     * update an already existing {@link Accreditation} for a  {@link Professional}) based on the provided request.
     *
     * @param request the {@link Professional} registration request
     * @return a {@link ProfessionalUpdateAccreditationResponse} indicating the success of the registration
     */
    ProfessionalUpdateAccreditationResponse updateAccreditation (ProfessionalUpdateAccreditationRequest request);



}
