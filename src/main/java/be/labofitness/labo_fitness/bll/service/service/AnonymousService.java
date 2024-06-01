package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachBySpecificationRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecificationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionBySpecificationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

import java.util.List;

/**
 * Service interface for managing {@code anonymous access}.
 */
public interface AnonymousService {

    //region COACH

    /**
     * Retrieves {@link Coach} based on specification.
     *
     * @param request the request containing the criteria for filtering {@link Coach} by specification
     * @return a list of {@link Coach} filtered by specification
     */
    List<GetCoachesResponse> getAllCoachesBySpecification(GetCoachBySpecificationRequest request);

    // endregion

    //region PHYSIOTHERAPIST

    /**
     * Retrieves {@link Physiotherapist} based on specification.
     *
     * @param request the request containing the criteria for filtering {@link Physiotherapist} by specification
     * @return a list of {@link Physiotherapist} filtered by specification
     */
    List<GetPhysioResponse> getAllPhysioBySpecification(GetPhysioBySpecificationRequest request);

    //endregion

    // region TRAINING SESSIONS


    List<GetTrainingSessionResponse> getAllTrainingSessionsBySpecification(GetTrainingSessionBySpecificationRequest request);

    // endregion

}
