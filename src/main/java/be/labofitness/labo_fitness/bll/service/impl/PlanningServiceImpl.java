package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.model.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PhysioPlanningRequest;
import be.labofitness.labo_fitness.bll.service.service.*;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.bll.specification.AppointmentSpecification;
import be.labofitness.labo_fitness.bll.specification.CompetitionSpecification;
import be.labofitness.labo_fitness.bll.specification.TrainingSpecification;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link PlanningService} interface.
 * Provides methods for managing various planning-related operations for :
 * <ol>
 *     <li>{@link Client}</li>
 *     <li>{@link Physiotherapist}</li>
 *     <li>{@link Coach}</li>
 * </ol>
 */
@RequiredArgsConstructor
@Service
public class PlanningServiceImpl implements PlanningService {

    private final ClientRepository clientRepository;
    private final CoachRepository coachRepository;
    private final PhysiotherapistRepository physiotherapistRepository;
    private final AppointmentService appointmentService;
    private final TrainingSessionService trainingService;
    private final SecurityService securityService;
    private final CompetitionService competitionService;
    private final SpecificationService specificationService;

    //region CLIENT

    /**
     * Retrieves all {@link Appointment} for a {@link Client} based on the provided request.
     *
     * @param request the request containing criteria for fetching {@code client appointments}
     * @return a list of {@link Appointment} matching the criteria
     */
    @Override
    public List<Appointment> getAllClientAppointments(ClientPlanningRequest request) {
        Specification<Appointment> spec = Specification
                .where(AppointmentSpecification.hasClient(securityService.getAuthentication(Client.class).getId())
                        .and(AppointmentSpecification.hasStatus(AppointmentStatus.ACCEPTED)));

        spec = specificationService.specificationHasSomething(spec,
                specificationService.getIdByMail(request.physiotherapistMail(), physiotherapistRepository),
                AppointmentSpecification::hasPhysiotherapist);

        spec = specificationService.specificationHasSomething(spec, request.name(), AppointmentSpecification::hasName);

        spec = specificationService.specificationHasSomething(spec, request.startDate(), AppointmentSpecification::hasStartDateAfter);

        spec = specificationService.specificationHasSomething(spec, request.endDate(), AppointmentSpecification::hasEndDateAfter);

        return appointmentService.findBySpecification(spec);
    }

    /**
     * Retrieves all {@link Competition} for a {@link Client} based on the provided request.
     *
     * @param request the request containing criteria for fetching {@code client competitions}
     * @return a list of {@link Competition} matching the criteria
     */
    @Override
    public List<Competition> getAllClientCompetitions(ClientPlanningRequest request) {
        Specification<Competition> spec = Specification
                .where(CompetitionSpecification.hasClient(securityService.getAuthentication(Client.class).getId()));

        spec = specificationService.specificationHasSomething(spec,
                specificationService.getIdByMail(request.coachMail(), coachRepository),
                CompetitionSpecification::hasCoach);

        spec = specificationService.specificationHasCollectionOfSomething(spec, request.sports(), CompetitionSpecification::hasSport);

        spec = specificationService.specificationHasSomething(spec, request.name(), CompetitionSpecification::hasName);

        spec = specificationService.specificationHasSomething(spec, request.startDate(), CompetitionSpecification::hasStartDateAfter);

        spec = specificationService.specificationHasSomething(spec, request.endDate(), CompetitionSpecification::hasEndDateBefore);

        return competitionService.getCompetitionBySpecification(spec);
    }


    /**
     * Retrieves all {@link TrainingSession} for a {@link Client} based on the provided request.
     *
     * @param request the request containing criteria for fetching {@code client training sessions}
     * @return a list of {@link TrainingSession} matching the criteria
     */
    @Override
    public List<TrainingSession> getAllClientTrainings(ClientPlanningRequest request) {
        Specification<TrainingSession> spec = Specification
                .where(TrainingSpecification.hasClient(securityService.getAuthentication(Client.class).getId()));

        spec = specificationService.specificationHasSomething(spec,
                specificationService.getIdByMail(request.coachMail(), coachRepository),
                TrainingSpecification::hasCoach);

        spec = specificationService.specificationHasSomething(spec, request.name(), TrainingSpecification::hasName);

        spec = specificationService.specificationHasSomething(spec, request.startDate(), TrainingSpecification::hasStartDateAfter);

        spec = specificationService.specificationHasSomething(spec, request.endDate(), TrainingSpecification::hasEndDateBefore);

        return trainingService.findBySpecifications(spec);
    }

    // endregion

    //region PHYSIO

    /**
     * Retrieves all {@link Appointment} for a {@link Physiotherapist} based on the provided request.
     *
     * @param request the request containing criteria for fetching {@code physio appointments}
     * @return a list of {@link Appointment} matching the criteria
     */
    @Override
    public List<Appointment> getAllPhysioAppointments(PhysioPlanningRequest request) {
        Specification<Appointment> spec = Specification
                .where(AppointmentSpecification.hasPhysiotherapist(securityService.getAuthentication(Physiotherapist.class).getId())
                        .and(AppointmentSpecification.hasStatus(AppointmentStatus.ACCEPTED)));

        spec = specificationService.specificationHasSomething(spec,
                specificationService.getIdByMail(request.clientEmail(), clientRepository),
                AppointmentSpecification::hasClient);

        spec = specificationService.specificationHasSomething(spec, request.name(), AppointmentSpecification::hasName);

        spec = specificationService.specificationHasSomething(spec, request.startDate(), AppointmentSpecification::hasStartDateAfter);

        spec = specificationService.specificationHasSomething(spec, request.endDate(), AppointmentSpecification::hasEndDateAfter);

        return appointmentService.findBySpecification(spec);
    }

    // endregion

    //region COACH

    /**
     * Retrieves all {@link Competition} for a {@link Coach} based on the provided request.
     *
     * @param request the request containing criteria for fetching {@code coach competitions}
     * @return a list of {@link Competition} matching the criteria
     */
    @Override
    public List<Competition> getAllCoachCompetitions(CoachPlanningRequest request) {
        Specification<Competition> spec = Specification
                .where(CompetitionSpecification.hasCoach(securityService.getAuthentication(Coach.class).getId()));

        spec = specificationService.specificationHasSomething(spec,
                specificationService.getIdByMail(request.clientEmail(), clientRepository),
                CompetitionSpecification::hasClient);

        spec = specificationService.specificationHasSomething(spec, request.name(), CompetitionSpecification::hasName);

        spec = specificationService.specificationHasCollectionOfSomething(spec, request.sports(), CompetitionSpecification::hasSport);

        spec = specificationService.specificationHasSomething(spec, request.startDate(), CompetitionSpecification::hasStartDateAfter);

        spec = specificationService.specificationHasSomething(spec, request.endDate(), CompetitionSpecification::hasEndDateBefore);

        return competitionService.getCompetitionBySpecification(spec);
    }

    /**
     * Retrieves all {@link TrainingSession} for a {@link Coach} based on the provided request.
     *
     * @param request the request containing criteria for fetching coach {@link TrainingSession}
     * @return a list of {@link TrainingSession} matching the criteria
     */
    @Override
    public List<TrainingSession> getAllCoachTrainings(CoachPlanningRequest request) {
        Specification<TrainingSession> spec = Specification
                .where(TrainingSpecification.hasCoach(securityService.getAuthentication(Coach.class).getId()));

        spec = specificationService.specificationHasSomething(spec,
                specificationService.getIdByMail(request.clientEmail(), coachRepository),
                TrainingSpecification::hasClient);

        spec = specificationService.specificationHasSomething(spec, request.name(), TrainingSpecification::hasName);


        spec = specificationService.specificationHasSomething(spec, request.startDate(), TrainingSpecification::hasStartDateAfter);

        spec = specificationService.specificationHasSomething(spec, request.endDate(), TrainingSpecification::hasEndDateBefore);

        return trainingService.findBySpecifications(spec);
    }

    // endregion

}
