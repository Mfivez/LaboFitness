package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.service.AnonymousService;
import be.labofitness.labo_fitness.dal.repository.UserRepository;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import lombok.RequiredArgsConstructor;
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

    private final UserRepository userRepository;

    // region GET PHYSIOTHERAPIST

    /**
     * Retrieves all {@link Physiotherapist}.
     *
     * @return a list of responses containing {@link Physiotherapist} details
     */
    @Override
    public List<GetPhysioResponse> getAllPhysio() {
        List<Physiotherapist> physio = userRepository.findAllPhysio();
        return physioToUserGetCoachesResponse(physio);
    }

    /**
     * Retrieves {@link Physiotherapist} by specialization.
     *
     * @param request the request containing the specialization
     * @return a list of responses containing {@link Physiotherapist} details
     */
    @Override
    public List<GetPhysioResponse> getPhysioBySpecialization(GetPhysioBySpecializationRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioBySpecialization(request.specialization());
        return physioToUserGetCoachesResponse(physio);
    }

    /**
     * Retrieves {@link Physiotherapist} by name.
     *
     * @param request the request containing the name
     * @return a list of responses containing {@link Physiotherapist} details
     */
    @Override
    public List<GetPhysioResponse> getPhysioByName(GetPhysioByNameRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioByName(request.name());
        return physioToUserGetCoachesResponse(physio);
    }

    /**
     * Converts a list of {@link Physiotherapist} to a list of {@link Physiotherapist} response objects.
     *
     * @param physio the list of {@link Physiotherapist}
     * @return a list of responses containing {@link Physiotherapist} details
     */
    public List<GetPhysioResponse>  physioToUserGetCoachesResponse(List<Physiotherapist> physio) {
        return physio.stream()
                .map(GetPhysioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region GET COACHES

    /**
     * Retrieves all {@link Coach}.
     *
     * @return a list of responses containing {@link Coach} details
     */
    @Override
    public List<GetCoachesResponse> getAllCoaches() {
        List<Coach> coaches = userRepository.findAllCoaches();
        return coachesToUserGetCoachesResponse(coaches);
    }

    /**
     * Retrieves {@link Coach} by remote status.
     *
     * @param request the request containing the remote status
     * @return a list of responses containing {@link Coach} details
     */
    @Override
    public List<GetCoachesResponse> getAllCoachesByIsRemote(GetCoachesByRemoteRequest request) {
        List<Coach> coaches = userRepository.findCoachesByIsRemote(request.is_remote());
        return coachesToUserGetCoachesResponse(coaches);

    }

    /**
     * Retrieves {@link Coach} by specialization.
     *
     * @param request the request containing the specialization
     * @return a list of responses containing {@link Coach} details
     */
    @Override
    public List<GetCoachesResponse> getAllCoachesBySpecialization(GetCoachesBySpecializationRequest request) {
        List<Coach> coaches = userRepository.findCoachesBySpecialization(request.specialization());
        return coachesToUserGetCoachesResponse(coaches);
    }

    /**
     * Retrieves {@link Coach} by name.
     *
     * @param request the request containing the name
     * @return a list of responses containing {@link Coach} details
     */
    @Override
    public List<GetCoachesResponse> getAllCoachesByName(GetCoachesByNameRequest request) {
        List<Coach> coaches = userRepository.findCoachesByName(request.name());
        return coachesToUserGetCoachesResponse(coaches);
    }

    /**
     * Converts a list of {@link Coach} to a list of {@link Coach} response objects.
     *
     * @param coaches the list of {@link Coach}
     * @return a list of responses containing {@link Coach} details
     */
    public List<GetCoachesResponse>  coachesToUserGetCoachesResponse(List<Coach> coaches) {
        return coaches.stream()
                .map(GetCoachesResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region TRAINING SESSIONS

    /**
     * Retrieves all {@link TrainingSession}.
     *
     * @return a list of responses containing {@link TrainingSession} details
     */
    @Override
    public List<GetTrainingSessionResponse> findAllTrainingSession() {
        List<TrainingSession> trainingSessions = userRepository.findAllTrainingSessions();
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves {@link TrainingSession} by recommended level.
     *
     * @param request the request containing the recommended level
     * @return a list of responses containing {@link TrainingSession} details
     */
    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByRecommendedLevel(request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves {@link TrainingSession} by duration.
     *
     * @param request the request containing the duration
     * @return a list of responses containing {@link TrainingSession} details
     */
    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByDuration(request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves {@link TrainingSession} by name.
     *
     * @param request the request containing the name
     * @return a list of responses containing {@link TrainingSession} details
     */
    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByName(GetTrainingSessionsByNameRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByName(request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves {@link TrainingSession} by {@link Coach}'s name.
     *
     * @param request the request containing the {@link Coach}'s name
     * @return a list of responses containing {@link TrainingSession} details
     */
    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByCoachName(request.coach_name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Converts a list of {@link TrainingSession} to a list of {@link TrainingSession} response objects.
     *
     * @param trainings the list of {@link TrainingSession}
     * @return a list of responses containing {@link TrainingSession} details
     */
    public List<GetTrainingSessionResponse>  trainingSessionToClientGetTrainingSessionResponse(List<TrainingSession> trainings) {
        return trainings.stream()
                .map(GetTrainingSessionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // endregion

}
