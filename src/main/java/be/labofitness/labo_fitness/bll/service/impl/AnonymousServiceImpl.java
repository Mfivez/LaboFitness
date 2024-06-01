package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachBySpecificationRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecificationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionBySpecificationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.service.*;
import be.labofitness.labo_fitness.bll.specification.CoachSpecification;
import be.labofitness.labo_fitness.bll.specification.PhysioSpecification;
import be.labofitness.labo_fitness.bll.specification.TrainingSpecification;
import be.labofitness.labo_fitness.dal.repository.CoachRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link AnonymousService} interface.
 * <br>Provides methods to retrieve various types of :
 * <ol>
 *     <li>{@link Professional}</li>
 *     <li>{@link TrainingSession}</li>
 * </ol>
 */
@RequiredArgsConstructor
@Service
public class AnonymousServiceImpl implements AnonymousService {

    private final SpecificationService specificationService;
    private final CoachService coachService;
    private final PhysiotherapistService physioService;
    private final TrainingSessionService trainingSessionService;
    private final CoachRepository coachRepository;

    // region GET PHYSIOTHERAPIST

    /**
     * Retrieves {@link Physiotherapist} based on specification.
     *
     * @param request the request containing the criteria for filtering {@link Physiotherapist} by specification
     * @return a list of {@link Physiotherapist} filtered by specification
     */
    @Override
    public List<GetPhysioResponse> getAllPhysioBySpecification(GetPhysioBySpecificationRequest request) {
        Specification<Physiotherapist> spec;
        if (request.clientId() == null) {
            spec = Specification.where(null);
        }
        else spec = Specification.where(PhysioSpecification.hasClientId(request.clientId()));

        spec = specificationService.specificationHasSomething(spec, request.name(), PhysioSpecification::hasName);

        spec = specificationService.specificationHasSomething(spec, request.email(), PhysioSpecification::hasEmail);

        spec = specificationService.specificationHasSomething(spec, request.lastname(), PhysioSpecification::hasLastName);

        spec = specificationService.specificationHasSomething(spec, request.specialization(), PhysioSpecification::hasSpecialization);

        return physioService.getPhysiotherapistBySpecification(spec).stream().map(GetPhysioResponse::fromEntity).collect(Collectors.toList());
    }

    //endregion

    // region GET COACHES

    /**
     * Retrieves {@link Coach} based on specification.
     *
     * @param request the request containing the criteria for filtering {@link Coach} by specification
     * @return a list of {@link Coach} filtered by specification
     */
    @Override
    public List<GetCoachesResponse> getAllCoachesBySpecification(GetCoachBySpecificationRequest request) {
        Specification<Coach> spec;
        if (request.clientId() == null) {
            spec = Specification.where(null);
        }
        else spec = Specification.where(CoachSpecification.hasASubscriptionToCompetitionOfCoach(request.clientId())
                .or(CoachSpecification.hasASubscriptionToTrainingOfCoach(request.clientId())));

        spec = specificationService.specificationHasSomething(spec, request.name(), CoachSpecification::hasName);

        spec = specificationService.specificationHasSomething(spec, request.email(), CoachSpecification::hasEmail);

        spec = specificationService.specificationHasSomething(spec, request.lastname(), CoachSpecification::hasLastName);

        spec = specificationService.specificationHasSomething(spec, request.pricePerHourMin(), CoachSpecification::hasPriceHourGreaterThanOrEqualTo);

        spec = specificationService.specificationHasSomething(spec, request.pricePerHourMax(), CoachSpecification::hasPriceHourLessThanOrEqualTo);

        spec = specificationService.specificationHasSomething(spec, request.isRemote(), CoachSpecification::isRemote);

        spec = specificationService.specificationHasSomething(spec, request.specialization(), CoachSpecification::hasSpecialization);

        return coachService.getCoachBySpecification(spec).stream().map(GetCoachesResponse::fromEntity).collect(Collectors.toList());
    }

    //endregion

    // region TRAINING SESSIONS

    /**
     * Retrieves {@link TrainingSession} based on specification.
     *
     * @param request the request containing the criteria for filtering {@link TrainingSession} by specification
     * @return a list of {@link TrainingSession} filtered by specification
     */
    @Override
    public List<GetTrainingSessionResponse> getAllTrainingSessionsBySpecification(GetTrainingSessionBySpecificationRequest request) {
        Specification<TrainingSession> spec;
        if (request.clientId() == null) {
            spec = Specification.where(null);
        }
        else spec = Specification.where(TrainingSpecification.hasClient(request.clientId()));

        spec = specificationService.specificationHasSomething(spec, request.trainingName(), TrainingSpecification::hasName);

        spec = specificationService.specificationHasSomething(
                spec,
                specificationService.getIdByMail(request.coachName(), coachRepository),
                TrainingSpecification::hasCoach);

        spec = specificationService.specificationHasSomething(spec, request.recommendedLevel(), TrainingSpecification::hasRecommendedLevel);

        spec = specificationService.specificationHasSomething(spec, request.isInscriptionOpen(), TrainingSpecification::isInscriptionOpen);

        return trainingSessionService.findBySpecifications(spec).stream().map(GetTrainingSessionResponse::fromEntity).collect(Collectors.toList());
    }

    // endregion

}
