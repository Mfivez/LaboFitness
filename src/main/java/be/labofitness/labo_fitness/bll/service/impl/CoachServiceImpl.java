package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.AlreadyExistException;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.exception.Unauthorize.UnauthorizedException;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionRequest;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionResponse;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations.CoachUpdateSpecificsInformationsRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations.CoachUpdateSpecificsInformationsResponse;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.CoachService;
import be.labofitness.labo_fitness.bll.service.service.CompetitionService;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.bll.service.service.TrainingSessionService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodName;
/**
 * Implementation of the {@link CoachService} interface.
 * <br>Provides operations for managing coach-related functionalities such as planning, account management, and event inscription.
 */
@RequiredArgsConstructor
@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final SecurityService securityService;
    private final PlanningService planningService;
    private final CompetitionService competitionService;
    private final TrainingSessionService trainingService;
    private final UserRepository userRepository;

    // region PLANNING

    /**
     * Retrieves the planning for a {@link Coach} based on the provided request.
     *
     * @param request The request containing parameters for filtering the planning.
     * @return A {@link PlanningResponse} object containing the planned events.
     */
    @Override
    public List<PlanningResponse> getPlanning(CoachPlanningRequest request) {
        return IntStream.range(0, getEventDetails(request, Competition::getStartDate, TrainingSession::getStartDate).size())
                .mapToObj(i -> new PlanningResponse(
                        getEventDetails(request, Competition::getStartDate, TrainingSession::getStartDate).get(i),
                        getEventDetails(request, Competition::getEndDate, TrainingSession::getEndDate).get(i),
                        getEventDetails(request, Competition::getName, TrainingSession::getName).get(i)))
                .collect(Collectors.toList());
    }

    private <T> List<T> getEventDetails(CoachPlanningRequest request, Function<Competition, T> compMapper, Function<TrainingSession, T> trainMapper) {
        boolean includeOnlyComp =
                request.sports() != null
                || (request.types() != null
                && request.types().contains("competition")
                && !request.types().contains("training"));

        boolean includeOnlyTrain =
                request.sports() == null
                && (request.types() != null
                &&  request.types().contains("training")
                && !(request.types().contains("competition")));

        if (includeOnlyComp) {
            return planningService.getAllCoachCompetitions(request).stream()
                    .map(compMapper)
                    .collect(Collectors.toList());
        }
        else if (includeOnlyTrain) {
            return planningService.getAllCoachTrainings(request).stream()
                    .map(trainMapper)
                    .collect(Collectors.toList());
        }
        else {
            List<T> details = planningService.getAllCoachTrainings(request).stream()
                    .map(trainMapper)
                    .collect(Collectors.toList());
            List<T> compDetails = planningService.getAllCoachCompetitions(request).stream()
                    .map(compMapper)
                    .toList();

            details.addAll(compDetails);
            return details;
        }
    }

    //endregion

    // region COACH MANAGE ACCOUNT

    /**
     * Manages the {@link Coach}'s account based on the provided request.
     *
     * @param request The request containing updated account information.
     * @return A {@link CoachManageAccountResponse} indicating the result of the account management operation.
     */
    @Override
    @Transactional
    public CoachManageAccountResponse manageAccount(CoachManageAccountRequest request) {

        Coach coach = securityService.getAuthentication(Coach.class);

        if (!coach.getEmail().equals(request.email())) {
            if (!userRepository.existsByEmail(request.email())) {
                coach.setEmail(request.email());
            } else {
                throw new AlreadyExistException("Email: " + request.email() + " already exists");
            }
        }

        coach.setName(request.name());
        coach.setLastname(request.lastName());
        coach.setGender(request.gender());
        coach.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
        coach.setRemote(request.isRemote());
        coach.setPriceHour(request.pricePerHour());
        coachRepository.save(coach);

        return CoachManageAccountResponse.fromEntity(coach, getCurrentMethodName());
    }

    /**
     * Update isRemote and pricePerHour of an {@link Coach} account
     * @param request of the {@link CoachUpdateSpecificsInformationsRequest} to update
     * @return response {@link CoachUpdateSpecificsInformationsResponse} with a message
     */
    @Override
    @Transactional
    public CoachUpdateSpecificsInformationsResponse updateSpecificInformations(CoachUpdateSpecificsInformationsRequest request){
        Coach coach = securityService.getAuthentication(Coach.class);

        coach.setRemote(request.isRemote());
        coach.setPriceHour(request.pricePerHour());
        coachRepository.save(coach);

        return CoachUpdateSpecificsInformationsResponse.fromEntity(coach, getCurrentMethodName());
    }


    // endregion

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Coach} by its ID.
     *
     * @param id the ID of the {@link Coach} to retrieve
     * @return the {@link Coach} with the given ID
     */
    @Override
    public Coach getOne(Long id) {return null;}

    @Override
    public Coach getOneByEmail(String email) {
        return coachRepository.findByEmail(email)
                .orElseThrow( () -> new DoesntExistException("Mail doesn't exist: " + email));
    }

    /**
     * Retrieves all {@link Coach}.
     *
     * @return a list of all {@link Coach}
     */
    @Override
    public List<Coach> getAll() {return coachRepository.findAll();}

    /**
     * Creates a new {@link Coach}.
     *
     * @param entity the {@link Coach} to create
     * @return the created {@link Coach}
     */
    @Override
    public Coach create(Coach entity) {return coachRepository.save(entity);}

    /**
     * Updates an existing {@link Coach}.
     *
     * @param entity the {@link Coach} to update
     * @return the updated {@link Coach}
     */
    @Override
    public Coach update(Coach entity) {return null;}

    /**
     * Deletes an {@link Coach} by its ID.
     *
     * @param id the ID of the {@link Coach} to delete
     * @return the deleted {@link Coach}, or null if not found
     */
    @Override
    public Coach delete(Long id) {return null;}

    // endregion

    //region EVENT INSCRIPTION MANAGEMENT

    /**
     * Manages the inscription state of a competition.
     *
     * @param request The request containing the {@link Competition} ID and the desired state.
     * @return A {@link ManageEventInscriptionResponse} indicating the result of the operation.
     * @throws UnauthorizedException If attempting to modify a {@link Competition} created by another {@link Coach}.
     */
    @Override @Transactional
    public ManageEventInscriptionResponse manageCompetitionInscription(ManageEventInscriptionRequest request) {
        String message;
        List<Competition> competitions = coachRepository.findPersonalCompetitionById(securityService.getAuthentication(Coach.class).getId());

        if (competitions.stream().map(Competition::getId).noneMatch(id -> id.equals(request.id()))) {
            throw new UnauthorizedException("You're not allowed to modify a competition created by an another coach");
        }

        Competition competition = competitionService.getOne(request.id());

        if (competition.isInscriptionOpen() == request.state()) {
            message = "Competition inscriptions already on " + request.state();
        }
        else {
            competition.setInscriptionOpen(request.state());
            competitionService.update(competition);
            message = "Competition inscription status = " + request.state();
        }

        return new ManageEventInscriptionResponse(message);
    }

    /**
     * Manages the inscription state of a {@link TrainingSession}.
     *
     * @param request The request containing the {@link TrainingSession} ID and the desired state.
     * @return A {@link ManageEventInscriptionResponse} indicating the result of the operation.
     * @throws UnauthorizedException If attempting to modify a training session created by another {@link Coach}.
     */
    @Override
    public ManageEventInscriptionResponse manageTrainingInscription(ManageEventInscriptionRequest request) {
        List<Competition> competitions = coachRepository.findPersonalCompetitionById(securityService.getAuthentication(Coach.class).getId());
        TrainingSession training = trainingService.getOne(request.id());
        String message;

        if (competitions.stream().map(Competition::getId).noneMatch(id -> id.equals(request.id()))) {
            throw new UnauthorizedException("You can't modify a training created by another coach: " + request.id());
        }

        if (training.isInscriptionOpen() == request.state()) {
            message = "Training inscriptions already on " + request.state() ;
        }
        else {
            training.setInscriptionOpen(request.state());
            trainingService.update(training);
            message = "Training inscription status = " + request.state();
        }

        return new ManageEventInscriptionResponse(message);
    }

    //endregion

    // region SPECIFICATION

    @Override
    public List<Coach> getCoachBySpecification(Specification<Coach> specification) {
        return coachRepository.findAll(specification);
    }

    // endregion

}
