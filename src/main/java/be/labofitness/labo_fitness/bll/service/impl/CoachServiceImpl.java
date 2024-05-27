package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.model.request.coach.ManageEventInscription.ManageEventInscriptionRequest;
import be.labofitness.labo_fitness.bll.model.request.coach.manageAccount.CoachManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.request.planning.CoachPlanningRequest;
import be.labofitness.labo_fitness.bll.model.response.coach.ManageEventInscription.ManageEventInscriptionResponse;
import be.labofitness.labo_fitness.bll.model.response.coach.manageAccount.CoachManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.response.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.service.service.CoachService;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.dal.repository.TrainingSessionRepository;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
//TODO REFACT IL FAUDRAIT QUE LES IMPL DE SERVICE N'APPELLENT QUE DES SERVICES ET PAS DES REPOS
public class CoachServiceImpl implements CoachService {

    private final UserRepository userRepository;  //TODO REFAC
    private final CoachRepository coachRepository;
    private final SecurityService securityService;
    private final PlanningService planningService;
    private final CompetitionRepository competitionRepository;  //TODO REFAC
    private final TrainingSessionRepository trainingSessionRepository;  //TODO REFAC

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

    @Override
    public CoachManageAccountResponse manageAccount(CoachManageAccountRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Coach coach = securityService.getAuthentication(Coach.class);
        coach.setName(request.name());
        coach.setLast_name(request.lastName());
        coach.setEmail(request.email());
        coach.setGender(request.gender());
        coach.setAdress(new Adress(request.street(), request.number(), request.city(), request.zipCode()));
        coach.setRemote(request.isRemote());
        coach.setPriceHour(request.pricePerHour());

        coachRepository.save(coach);
        return new CoachManageAccountResponse("Account modified with success");
    }

    // endregion

    // region CLASSIC CRUD

    @Override
    public Coach getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Coach> getAll() {
        return List.of();
    }

    @Override
    public Coach create(Coach entity) {
        return null;
    }

    @Override
    public Coach update(Coach entity) {
        return null;
    }

    @Override
    public Coach delete(Long aLong) {
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

        if (competition.isInscriptionIsOpen() == request.state()) {
            message = "Competition inscriptions already on " + request.state();
        }
        else {
            competition.setInscriptionIsOpen(request.state());
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
