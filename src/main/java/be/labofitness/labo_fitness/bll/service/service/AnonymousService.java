package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.*;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;

import java.util.List;

/**
 * Service interface for managing {@code anonymous access}.
 */
public interface AnonymousService {

    //region COACH

    /**
     * Retrieves all {@link Coach}.
     *
     * @return a list of all {@link Coach}
     */
    List<GetCoachesResponse> getAllCoaches();

    /**
     * Retrieves {@link Coach} based on remote availability.
     *
     * @param request the request containing the criteria for filtering {@link Coach} by remote availability
     * @return a list of {@link Coach} filtered by remote availability
     */
    List<GetCoachesResponse> getAllCoachesByIsRemote(GetCoachesByRemoteRequest request);

    /**
     * Retrieves {@link Coach} based on specialization.
     *
     * @param request the request containing the criteria for filtering {@link Coach} by specialization
     * @return a list of {@link Coach} filtered by specialization
     */
    List<GetCoachesResponse> getAllCoachesBySpecialization(GetCoachesBySpecializationRequest request);

    /**
     * Retrieves {@link Coach} based on name.
     *
     * @param request the request containing the criteria for filtering {@link Coach} by name
     * @return a list of {@link Coach} filtered by name
     */
    List<GetCoachesResponse> getAllCoachesByName(GetCoachesByNameRequest request);

    // endregion

    //region PHYSIOTHERAPIST

    /**
     * Retrieves all {@link Physiotherapist}.
     *
     * @return a list of all {@link Physiotherapist}
     */
    List<GetPhysioResponse> getAllPhysio();

    /**
     * Retrieves {@link Physiotherapist} based on specialization.
     *
     * @param request the request containing the criteria for filtering {@link Physiotherapist} by specialization
     * @return a list of {@link Physiotherapist} filtered by specialization
     */
    List<GetPhysioResponse> getPhysioBySpecialization(GetPhysioBySpecializationRequest request);

    /**
     * Retrieves {@link Physiotherapist} based on name.
     *
     * @param request the request containing the criteria for filtering {@link Physiotherapist} by name
     * @return a list of {@link Physiotherapist} filtered by name
     */
    List<GetPhysioResponse> getPhysioByName(GetPhysioByNameRequest request);

    //endregion

    // region TRAINING SESSIONS

    /**
     * Retrieves all {@link TrainingSession}.
     *
     * @return a list of all {@link TrainingSession}
     */
    List<GetTrainingSessionResponse> findAllTrainingSession();

    /**
     * Retrieves {@link TrainingSession} based on recommended level.
     *
     * @param request the request containing the criteria for filtering {@link TrainingSession} by recommended level
     * @return a list of {@link TrainingSession} filtered by recommended level
     */
    List<GetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request);

    /**
     * Retrieves {@link TrainingSession} based on duration.
     *
     * @param request the request containing the criteria for filtering {@link TrainingSession} by duration
     * @return a list of {@link TrainingSession} filtered by duration
     */
    List<GetTrainingSessionResponse> findTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request);

    /**
     * Retrieves {@link TrainingSession} based on name.
     *
     * @param request the request containing the criteria for filtering {@link TrainingSession} by name
     * @return a list of {@link TrainingSession} filtered by name
     */
    List<GetTrainingSessionResponse> findTrainingSessionByName(GetTrainingSessionsByNameRequest request);

    /**
     * Retrieves {@link TrainingSession} based on coach name.
     *
     * @param request the request containing the criteria for filtering {@link TrainingSession} by coach name
     * @return a list of {@link TrainingSession} filtered by coach name
     */
    List<GetTrainingSessionResponse> findTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request);

    // endregion

}
