package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.PhysiotherapistService;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PhysiotherapistServiceImpl implements PhysiotherapistService {

    private final PlanningService planningService;
    private final PhysiotherapistRepository physiotherapistRepository;

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

    /**
     * Retrieves an {@link Physiotherapist} by its ID.
     *
     * @param id the ID of the {@link Physiotherapist} to retrieve
     * @return the {@link Physiotherapist} with the given ID
     */
    @Override
    public Physiotherapist getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Physiotherapist}.
     *
     * @return a list of all {@link Physiotherapist}
     */
    @Override
    public List<Physiotherapist> getAll() {
        return physiotherapistRepository.findAll();
    }

    /**
     * Creates a new {@link Physiotherapist}.
     *
     * @param entity the {@link Physiotherapist} to create
     * @return the created {@link Physiotherapist}
     */
    @Override
    public Physiotherapist create(Physiotherapist entity) {
        return null;
    }

    /**
     * Updates an existing {@link Physiotherapist}.
     *
     * @param entity the {@link Physiotherapist} to update
     * @return the updated {@link Physiotherapist}
     */
    @Override
    public Physiotherapist update(Physiotherapist entity) {
        return null;
    }

    /**
     * Deletes an {@link Physiotherapist} by its ID.
     *
     * @param id the ID of the {@link Physiotherapist} to delete
     * @return the deleted {@link Physiotherapist}, or null if not found
     */
    @Override
    public Physiotherapist delete(Long id) {
        return null;
    }

    // endregion
}
