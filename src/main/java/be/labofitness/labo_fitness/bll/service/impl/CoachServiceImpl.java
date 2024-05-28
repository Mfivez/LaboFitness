package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.exception.notMatching.PasswordNotMatchingException;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionRequest;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription.ManageEventInscriptionResponse;
import be.labofitness.labo_fitness.bll.model.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.model.request.coach.manageAccount.changePassword.CoachChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.request.physiotherapist.manageAccount.PhysiotherapistManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.response.coach.manageAccount.changePassword.CoachChangePasswordResponse;
import be.labofitness.labo_fitness.bll.model.response.physiotherapist.manageAccount.PhysiotherapistManageAccountResponse;
import be.labofitness.labo_fitness.bll.service.service.CoachService;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.dal.repository.TrainingSessionRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//TODO METH
//import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodeName;

@RequiredArgsConstructor
@Service
//TODO REFACT IL FAUDRAIT QUE LES IMPL DE SERVICE N'APPELLENT QUE DES SERVICES ET PAS DES REPOS
public class CoachServiceImpl implements CoachService {

    private final UserRepository userRepository;  //TODO REFAC
    private final CoachRepository coachRepository;
    private final SecurityService securityService;
    private final PlanningService planningService;
    private final CompetitionRepository competitionRepository;  //TODO REFAC
    private final TrainingSessionRepository trainingSessionRepository;
    private final PasswordEncoder passwordEncoder;//TODO REFAC

    // region PLANNING
    //TODO TRANSFER METHODS GET THINGS NOT OVERRIDE ON PLANNING SERVICE
    @Override
    public PlanningResponse getPlanning(CoachPlanningRequest request) {
        return new PlanningResponse(
                getStartDates(request),
                getEndDates(request),
                getEventNames(request)
        );
    }

    private List<LocalDateTime> getStartDates(CoachPlanningRequest request) {
        boolean includeOnlyComp = request.sports() != null;
        boolean includeAll = (request.types() == null || request.types().isEmpty()) && !includeOnlyComp;

        if (includeOnlyComp || includeAll) {
            return planningService.getAllCoachCompetitions(request).stream()
                    .map(Competition::getStartDate)
                    .collect(Collectors.toList());
        } else {
            return planningService.getAllCoachTrainings(request).stream()
                    .map(TrainingSession::getStartDate)
                    .collect(Collectors.toList());
        }
    }

    private List<LocalDateTime> getEndDates(CoachPlanningRequest request) {
        boolean includeOnlyComp = request.sports() != null;
        boolean includeAll = request.types() == null || request.types().isEmpty();

        if (includeOnlyComp || includeAll) {
            return planningService.getAllCoachCompetitions(request).stream()
                    .map(Competition::getEndDate)
                    .collect(Collectors.toList());
        } else {
            return planningService.getAllCoachTrainings(request).stream()
                    .map(TrainingSession::getEndDate)
                    .collect(Collectors.toList());
        }
    }

    private List<String> getEventNames(CoachPlanningRequest request) {
        boolean includeOnlyComp = request.sports() != null;
        boolean includeAll = request.types() == null || request.types().isEmpty();

        if (includeOnlyComp || includeAll || request.types().contains("competition")) {
            return planningService.getAllCoachCompetitions(request).stream()
                    .map(Competition::getName)
                    .collect(Collectors.toList());
        } else {
            return planningService.getAllCoachTrainings(request).stream()
                    .map(TrainingSession::getName)
                    .collect(Collectors.toList());
        }
    }

    //endregion

    // region COACH MANAGE ACCOUNT

    /**
     * Update an {@link Coach} account
     * @param request of the {@link CoachManageAccountRequest} to update
     * @return response {@link CoachManageAccountResponse} with a message
     */
    @Override
    @Transactional
    public CoachManageAccountResponse manageAccount(CoachManageAccountRequest request) {

        String message  = "getCurrentMethodeName()";
        Coach coach = securityService.getAuthentication(Coach.class);

        if (!coach.getEmail().equals(request.email())) {
            if (!userRepository.existsByEmail(request.email())) {  coach.setEmail(request.email());  }
            else{
                throw new PasswordNotMatchingException("Email already exists");
            }
        }
        coach.setName(request.name());
        coach.setLastname(request.lastName());
        coach.setGender(request.gender());
        coach.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
        coach.setRemote(request.isRemote());
        coach.setPriceHour(request.pricePerHour());

        coachRepository.save(coach);

        return CoachManageAccountResponse.fromEntity(coach,message);


    }

    /**
     * Update the password of an {@link Coach} account
     * @param request of the {@link CoachChangePasswordRequest} to update
     * @return response {@link CoachChangePasswordResponse} with a message
     */
    @Override
    @Transactional
    public CoachChangePasswordResponse changePassword(CoachChangePasswordRequest request) {

        String message = "getCurrentMethodeName()";

        Coach coach = securityService.getAuthentication(Coach.class);

        if(!passwordEncoder.matches(request.oldPassword(),coach.getPassword())){

            throw new PasswordNotMatchingException("passwords are not matching");
        }
        coach.setPassword(passwordEncoder.encode(request.newPassword()));
        coachRepository.save(coach);

        return CoachChangePasswordResponse.fromEntity(coach, message);
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
    public Coach getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Coach}.
     *
     * @return a list of all {@link Coach}
     */
    @Override
    public List<Coach> getAll() {
        return coachRepository.findAll();
    }

    /**
     * Creates a new {@link Coach}.
     *
     * @param entity the {@link Coach} to create
     * @return the created {@link Coach}
     */
    @Override
    public Coach create(Coach entity) {
        return null;
    }

    /**
     * Updates an existing {@link Coach}.
     *
     * @param entity the {@link Coach} to update
     * @return the updated {@link Coach}
     */
    @Override
    public Coach update(Coach entity) {
        return null;
    }

    /**
     * Deletes an {@link Coach} by its ID.
     *
     * @param id the ID of the {@link Coach} to delete
     * @return the deleted {@link Coach}, or null if not found
     */
    @Override
    public Coach delete(Long id) {
        return null;
    }

    // endregion

    //region EVENT INSCRIPTION MANAGEMENT

    @Override @Transactional
    public ManageEventInscriptionResponse manageCompetitionInscription(ManageEventInscriptionRequest request) {
        List<Competition> competitions = coachRepository.findPersonalCompetitionById(securityService.getAuthentication(Coach.class).getId());
        String message;
        if (competitions.stream().map(Competition::getId).noneMatch(id -> id.equals(request.id()))) {
            throw new RuntimeException("You can't modify a competition created by another coach");
        }

        Competition competition = competitionRepository.findByCompetitionId(request.id())
                .orElseThrow( () -> new IllegalArgumentException("Competition doesn't exist"));

        if (competition.isInscriptionOpen() == request.state()) {
            message = "Competition inscriptions already on " + request.state();
        }
        else {
            competition.setInscriptionOpen(request.state());
            competitionRepository.save(competition);
            message = "Competition inscription status = " + request.state();
        }

        return new ManageEventInscriptionResponse(message);
    }

    @Override
    public ManageEventInscriptionResponse manageTrainingInscription(ManageEventInscriptionRequest request) {
        List<Competition> competitions = coachRepository.findPersonalCompetitionById(securityService.getAuthentication(Coach.class).getId());
        String message;
        if (competitions.stream().map(Competition::getId).noneMatch(id -> id.equals(request.id()))) {
            throw new RuntimeException("You can't modify a training created by another coach");
        }

        TrainingSession training = trainingSessionRepository.findTrainingSessionById(request.id())
                .orElseThrow( () -> new IllegalArgumentException("training doesn't exist"));

        if (training.isInscriptionOpen() == request.state()) {
            message = "Training inscriptions already on " + request.state() ;
        }
        else {
            training.setInscriptionOpen(request.state());
            trainingSessionRepository.save(training);
            message = "Training inscription status = " + request.state();
        }

        return new ManageEventInscriptionResponse(message);
    }

    //endregion

}
