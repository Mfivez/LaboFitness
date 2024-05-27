package be.labofitness.labo_fitness.bll.service.service;


import be.labofitness.labo_fitness.bll.model.request.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.request.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.request.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;

import java.util.List;

public interface PlanningService {

    List<Appointment> getAllClientAppointments(ClientPlanningRequest request);
    List<Competition> getAllClientCompetitions(ClientPlanningRequest request);
    List<TrainingSession> getAllClientTrainings(ClientPlanningRequest request);

    List<Appointment> getAllPhysioAppointments(PhysioPlanningRequest request);

    List<Competition> getAllCoachCompetitions(CoachPlanningRequest request);
    List<TrainingSession> getAllCoachTrainings(CoachPlanningRequest request);

}
