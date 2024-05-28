package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;

import java.util.List;

/**
 * Service interface for managing {@code planning}.
 */
public interface PlanningService {

    //region CLIENT

    /**
     * Retrieves all {@link Appointment} for a {@link Client} based on the provided request.
     *
     * @param request the {@link Client} planning request
     * @return a list of {@link Appointment}  for the {@link Client}
     */
    List<Appointment> getAllClientAppointments(ClientPlanningRequest request);

    /**
     * Retrieves all {@link Competition} for a {@link Client} based on the provided request.
     *
     * @param request the {@link Client} planning request
     * @return a list of {@link Competition} for the {@link Client}
     */
    List<Competition> getAllClientCompetitions(ClientPlanningRequest request);

    /**
     * Retrieves all {@link TrainingSession} for a {@link Client} based on the provided request.
     *
     * @param request the {@link Client} planning request
     * @return a list of {@link TrainingSession} for the {@link Client}
     */
    List<TrainingSession> getAllClientTrainings(ClientPlanningRequest request);

    //endregion

    //region PHYSIOTHERAPIST

    /**
     * Retrieves all {@link Appointment} for a {@link Physiotherapist} based on the provided request.
     *
     * @param request the {@link Physiotherapist}  planning request
     * @return a list of {@link Appointment}  for the {@link Physiotherapist}
     */
    List<Appointment> getAllPhysioAppointments(PhysioPlanningRequest request);

    //endregion

    //region COACH

    /**
     * Retrieves all {@link Competition} for a {@link Coach} based on the provided request.
     *
     * @param request the {@link Coach} planning request
     * @return a list of {@link Competition} for the {@link Coach}
     */
    List<Competition> getAllCoachCompetitions(CoachPlanningRequest request);

    /**
     * Retrieves all {@link TrainingSession} for a {@link Coach} based on the provided request.
     *
     * @param request the {@link Coach} planning request
     * @return a list of {@link TrainingSession} for the {@link Coach}
     */
    List<TrainingSession> getAllCoachTrainings(CoachPlanningRequest request);

    //endregion

}
