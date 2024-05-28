package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.register.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.model.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
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

}
