package be.labofitness.labo_fitness.bll.service.service;

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

import java.util.List;

public interface AnonymousService {

    //region COACH

    List<GetCoachesResponse> getAllCoaches();

    List<GetCoachesResponse> getAllCoachesByIsRemote(GetCoachesByRemoteRequest request);

    List<GetCoachesResponse> getAllCoachesBySpecialization(GetCoachesBySpecializationRequest request);

    List<GetCoachesResponse> getAllCoachesByName(GetCoachesByNameRequest request);

    // endregion

    //region PHYSIOTHERAPIST

    List<GetPhysioResponse> getAllPhysio();

    List<GetPhysioResponse> getPhysioBySpecialization(GetPhysioBySpecializationRequest request);

    List<GetPhysioResponse> getPhysioByName(GetPhysioByNameRequest request);
    //endregion

    // region TRAINING SESSIONS

    List<GetTrainingSessionResponse> findAllTrainingSession();

    List<GetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request);

    List<GetTrainingSessionResponse> findTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request);

    List<GetTrainingSessionResponse> findTrainingSessionByName(GetTrainingSessionsByNameRequest request);

    List<GetTrainingSessionResponse> findTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request);

    // endregion

}
