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

@Service
@RequiredArgsConstructor
public class PhysiotherapistServiceImpl implements PhysiotherapistService {

    private final PlanningService planningService;

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
}
