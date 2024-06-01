package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

// TODO dev a logic for the nomination's of appointments
/**
 * Service interface for managing {@link Physiotherapist}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface PhysiotherapistService extends CrudService<Physiotherapist, Long> {

    // region PLANNING

    /**
     * Retrieves the planning for a {@link Physiotherapist} based on the provided request.
     *
     * @param request the {@link Physiotherapist} planning request
     * @return the planning response containing the {@code appointments}, start dates, and end dates
     */
    List<PlanningResponse> getPlanning(PhysioPlanningRequest request) ;

    // endregion

    // region ACCOUNT MANAGEMENT

    /**
     * Manages the account of a {@link Physiotherapist} based on the provided request.
     *
     * @param request the manage account request
     * @return the manage account response
     */
    PhysiotherapistManageAccountResponse manageAccount(PhysiotherapistManageAccountRequest request);

    // endregion

    //region UTILS

    /**
     * Retrieves a {@link Physiotherapist} entity by its email address.
     *
     * @param email the email address of the physiotherapist
     * @return the {@link Physiotherapist} entity with the specified email address
     */
    Physiotherapist getOneByEmail(String email);

    //endregion

    // region SPECIFICATION

    List<Physiotherapist> getPhysiotherapistBySpecification(Specification<Physiotherapist> specification);

    // endregion

}
