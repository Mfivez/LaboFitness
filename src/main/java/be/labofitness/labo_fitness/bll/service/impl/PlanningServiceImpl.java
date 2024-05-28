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

import java.time.LocalDateTime;
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

    private final ClientRepository clientRepository;  //TODO REFAC
    private final AppointmentRepository appointmentRepository;  //TODO REFAC
    private final TrainingSessionRepository trainingSessionRepository;  //TODO REFAC
    private final SecurityService securityService;
    private final PhysiotherapistRepository physiotherapistRepository;  //TODO REFAC
    private final CoachRepository coachRepository;  //TODO REFAC
    private final CompetitionRepository competitionRepository;  //TODO REFAC

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

        if (request.physiotherapistMail() != null && !request.physiotherapistMail().isEmpty()) {
            spec = spec.and(AppointmentSpecification.hasPhysiotherapist(
                    physiotherapistRepository.findByEmail(request.physiotherapistMail())
                    .orElseThrow( () -> new IllegalArgumentException("Physio doesn't exist")).getId()));  }

        spec = appointmentSpecificationHasName(spec, request.name());
        spec = appointmentSpecificationHasStartDateAfter(spec, request.startDate());
        spec = appointmentSpecificationHasEndDateAfter(spec, request.endDate());

        return appointmentRepository.findAll(spec);
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

        if (request.coachMail() != null && !request.coachMail().isEmpty()) {
            spec = spec.and(TrainingSpecification.hasCoach(
                    coachRepository.findByEmail(request.coachMail())
                            .orElseThrow( () -> new IllegalArgumentException("Coach doesn't exist")).getId()));  }

        spec = trainingSpecificationHasName(spec, request.name());
        spec = trainingSpecificationHasStartDateAfter(spec, request.startDate());
        spec = trainingSpecificationHasEndDateBefore(spec, request.endDate());

        return trainingSessionRepository.findAll(spec);
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

        if (request.clientEmail() != null && !request.clientEmail().isEmpty()) {
            spec = spec.and(AppointmentSpecification.hasClient(
                    clientRepository.findByEmail(request.clientEmail())
                            .orElseThrow( () -> new IllegalArgumentException("Client doesn't exist")).getId()));  }

        spec = appointmentSpecificationHasName(spec, request.name());
        spec = appointmentSpecificationHasStartDateAfter(spec, request.startDate());
        spec = appointmentSpecificationHasEndDateAfter(spec, request.endDate());

        return appointmentRepository.findAll(spec);
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

        if (request.clientEmail() != null && !request.clientEmail().isEmpty()) {
            spec = spec.and(TrainingSpecification.hasClient(
                    clientRepository.findByEmail(request.clientEmail())
                            .orElseThrow( () -> new IllegalArgumentException("Client doesn't exist")).getId()));  }

        spec = trainingSpecificationHasName(spec, request.name());
        spec = trainingSpecificationHasStartDateAfter(spec, request.startDate());
        spec = trainingSpecificationHasEndDateBefore(spec, request.endDate());

        return trainingSessionRepository.findAll(spec);
    }

    // endregion

    // region UTILS METHODS

    /**
     * Adds a {@link Specification} to filter {@link TrainingSession} by name if the name is not null or empty.
     *
     * @param spec the base {@link Specification} to add the filter to
     * @param name the name to filter by
     * @return the updated {@link Specification} with the name filter added
     */
    private Specification<TrainingSession> trainingSpecificationHasName(Specification<TrainingSession> spec, String name) {
        if (name != null && !name.isEmpty()) {
            spec = spec.and(TrainingSpecification.hasName(name));  }
        return spec;
    }

    /**
     * Adds a {@link Specification} to filter {@link TrainingSession} that end before a given date and time.
     *
     * @param spec the base {@link Specification} to add the filter to
     * @param ldt the date and time to filter by
     * @return the updated {@link Specification} with the end date filter added
     */
    private Specification<TrainingSession> trainingSpecificationHasEndDateBefore(Specification<TrainingSession> spec, LocalDateTime ldt) {
        if (ldt != null) {
            spec = spec.and(TrainingSpecification.hasEndDateBefore(ldt));  }
        return spec;
    }

    /**
     * Adds a {@link Specification} to filter {@link TrainingSession} that start after a given date and time.
     *
     * @param spec the base {@link Specification} to add the filter to
     * @param ldt the date and time to filter by
     * @return the updated {@link Specification} with the start date filter added
     */
    private Specification<TrainingSession> trainingSpecificationHasStartDateAfter(Specification<TrainingSession> spec, LocalDateTime ldt) {
        if (ldt != null) {
            spec = spec.and(TrainingSpecification.hasStartDateAfter(ldt));  }
        return spec;
    }

    /**
     * Adds a {@link Specification} to filter {@link Appointment} by name if the name is not null or empty.
     *
     * @param spec the base {@link Specification} to add the filter to
     * @param name the name to filter by
     * @return the updated {@link Specification} with the name filter added
     */
    private Specification<Appointment> appointmentSpecificationHasName(Specification<Appointment> spec ,String name) {
        if (name != null && !name.isEmpty()) {
            spec = spec.and(AppointmentSpecification.hasName(name));  }
        return spec;
    }

    /**
     * Adds a {@link Specification} to filter {@link Appointment} that start after a given date and time.
     *
     * @param spec the base {@link Specification} to add the filter to
     * @param ldt the date and time to filter by
     * @return the updated {@link Specification} with the start date filter added
     */
    private Specification<Appointment> appointmentSpecificationHasStartDateAfter(Specification<Appointment> spec ,LocalDateTime ldt) {
        if (ldt != null) {
            spec = spec.and(AppointmentSpecification.hasStartDateAfter(ldt));  }
        return spec;
    }

    /**
     * Adds a {@link Specification} to filter {@link Appointment} that end after a given date and time.
     *
     * @param spec the base {@link Specification} to add the filter to
     * @param ldt the date and time to filter by
     * @return the updated {@link Specification} with the end date filter added
     */
    private Specification<Appointment> appointmentSpecificationHasEndDateAfter(Specification<Appointment> spec ,LocalDateTime ldt) {
        if (ldt != null) {
            spec = spec.and(AppointmentSpecification.hasEndDateAfter(ldt));  }
        return spec;
    }

    // endregion

}
