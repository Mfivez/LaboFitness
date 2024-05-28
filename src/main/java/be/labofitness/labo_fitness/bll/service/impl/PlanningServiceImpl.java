package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.model.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.bll.specification.AppointmentSpecification;
import be.labofitness.labo_fitness.bll.specification.CompetitionSpecification;
import be.labofitness.labo_fitness.bll.specification.TrainingSpecification;
import be.labofitness.labo_fitness.dal.repository.*;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlanningServiceImpl implements PlanningService {

    private final ClientRepository clientRepository;  //TODO REFAC
    private final AppointmentRepository appointmentRepository;  //TODO REFAC
    private final TrainingSessionRepository trainingSessionRepository;  //TODO REFAC
    private final SecurityService securityService;
    private final PhysiotherapistRepository physiotherapistRepository;  //TODO REFAC
    private final CoachRepository coachRepository;  //TODO REFAC
    private final CompetitionRepository competitionRepository;  //TODO REFAC

    //region CLIENT

    @Override
    public List<Appointment> getAllClientAppointments(ClientPlanningRequest request) {
        Specification<Appointment> spec = Specification
                .where(AppointmentSpecification.hasClient(securityService.getAuthentication(Client.class).getId())
                        .and(AppointmentSpecification.hasStatus(AppointmentStatus.ACCEPTED)));

        if (request.physiotherapistMail() != null && !request.physiotherapistMail().isEmpty()) {
            spec = spec.and(AppointmentSpecification.hasPhysiotherapist(
                    physiotherapistRepository.findByEmail(request.physiotherapistMail())
                    .orElseThrow( () -> new IllegalArgumentException("Physio doesn't exist")).getId()));  }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(AppointmentSpecification.hasName(request.name()));  }

        if (request.startDate() != null) {
            spec = spec.and(AppointmentSpecification.hasStartDateAfter(request.startDate()));  }

        if (request.endDate() != null) {
            spec = spec.and(AppointmentSpecification.hasEndDateAfter(request.endDate()));  }

        return appointmentRepository.findAll(spec);
    }

    @Override
    public List<Competition> getAllClientCompetitions(ClientPlanningRequest request) {
        Specification<Competition> spec = Specification
                .where(CompetitionSpecification.hasClient(securityService.getAuthentication(Client.class).getId()));

        if (request.coachMail() != null && !request.coachMail().isEmpty()) {
            spec = spec.and(CompetitionSpecification.hasCoach(
                    coachRepository.findByEmail(request.coachMail())
                            .orElseThrow( () -> new IllegalArgumentException("Coach doesn't exist")).getId()));  }

        if (request.sports() != null && !request.sports().isEmpty()) {
            for (String sport : request.sports()) { spec = spec.and(CompetitionSpecification.hasSport(sport)); } }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(CompetitionSpecification.hasName(request.name()));  }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(CompetitionSpecification.hasName(request.name()));  }

        if (request.startDate() != null) {
            spec = spec.and(CompetitionSpecification.hasStartDateAfter(request.startDate()));  }

        if (request.endDate() != null) {
            spec = spec.and(CompetitionSpecification.hasEndDateBefore(request.endDate()));  }

        return competitionRepository.findAll(spec);
    }

    @Override
    public List<TrainingSession> getAllClientTrainings(ClientPlanningRequest request) {
        Specification<TrainingSession> spec = Specification
                .where(TrainingSpecification.hasClient(securityService.getAuthentication(Client.class).getId()));

        if (request.coachMail() != null && !request.coachMail().isEmpty()) {
            spec = spec.and(TrainingSpecification.hasCoach(
                    coachRepository.findByEmail(request.coachMail())
                            .orElseThrow( () -> new IllegalArgumentException("Coach doesn't exist")).getId()));  }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(TrainingSpecification.hasName(request.name()));  }

        if (request.startDate() != null) {
            spec = spec.and(TrainingSpecification.hasStartDateAfter(request.startDate()));  }

        if (request.endDate() != null) {
            spec = spec.and(TrainingSpecification.hasEndDateBefore(request.endDate()));  }

        return trainingSessionRepository.findAll(spec);
    }

    // endregion

    //region PHYSIO

    @Override
    public List<Appointment> getAllPhysioAppointments(PhysioPlanningRequest request) {
        Specification<Appointment> spec = Specification
                .where(AppointmentSpecification.hasPhysiotherapist(securityService.getAuthentication(Physiotherapist.class).getId())
                        .and(AppointmentSpecification.hasStatus(AppointmentStatus.ACCEPTED)));

        if (request.clientEmail() != null && !request.clientEmail().isEmpty()) {
            spec = spec.and(AppointmentSpecification.hasClient(
                    clientRepository.findByEmail(request.clientEmail())
                            .orElseThrow( () -> new IllegalArgumentException("Client doesn't exist")).getId()));  }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(AppointmentSpecification.hasName(request.name()));  }

        if (request.startDate() != null) {
            spec = spec.and(AppointmentSpecification.hasStartDateAfter(request.startDate()));  }

        if (request.endDate() != null) {
            spec = spec.and(AppointmentSpecification.hasEndDateAfter(request.endDate()));  }

        return appointmentRepository.findAll(spec);
    }

    // endregion

    //region COACH

    @Override
    public List<Competition> getAllCoachCompetitions(CoachPlanningRequest request) {
        Specification<Competition> spec = Specification
                .where(CompetitionSpecification.hasCoach(securityService.getAuthentication(Coach.class).getId()));

        if (request.clientEmail() != null && !request.clientEmail().isEmpty()) {
            spec = spec.and(CompetitionSpecification.hasClient(
                    clientRepository.findByEmail(request.clientEmail())
                            .orElseThrow( () -> new IllegalArgumentException("Client doesn't exist")).getId()));  }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(CompetitionSpecification.hasName(request.name()));  }

        if (request.sports() != null && !request.sports().isEmpty()) {
            for (String sport : request.sports()) { spec = spec.and(CompetitionSpecification.hasSport(sport)); } }

        if (request.startDate() != null) {
            spec = spec.and(CompetitionSpecification.hasStartDateAfter(request.startDate()));  }

        if (request.endDate() != null) {
            spec = spec.and(CompetitionSpecification.hasEndDateBefore(request.endDate()));  }

        return competitionRepository.findAll(spec);
    }

    @Override
    public List<TrainingSession> getAllCoachTrainings(CoachPlanningRequest request) {
        Specification<TrainingSession> spec = Specification
                .where(TrainingSpecification.hasCoach(securityService.getAuthentication(Coach.class).getId()));

        if (request.clientEmail() != null && !request.clientEmail().isEmpty()) {
            spec = spec.and(TrainingSpecification.hasClient(
                    clientRepository.findByEmail(request.clientEmail())
                            .orElseThrow( () -> new IllegalArgumentException("Client doesn't exist")).getId()));  }

        if (request.name() != null && !request.name().isEmpty()) {
            spec = spec.and(TrainingSpecification.hasName(request.name()));  }

        if (request.startDate() != null) {
            spec = spec.and(TrainingSpecification.hasStartDateAfter(request.startDate()));  }

        if (request.endDate() != null) {
            spec = spec.and(TrainingSpecification.hasEndDateBefore(request.endDate()));  }

        return trainingSessionRepository.findAll(spec);
    }

    // endregion


}
