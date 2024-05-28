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
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnonymousServiceImpl implements AnonymousService {

    private final UserRepository userRepository;

    // region GET PHYSIOTHERAPIST

    @Override
    public List<GetPhysioResponse> getAllPhysio() {
        List<Physiotherapist> physio = userRepository.findAllPhysio();
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPhysioBySpecialization(GetPhysioBySpecializationRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioBySpecialization(request.specialization());
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPhysioByName(GetPhysioByNameRequest request) {
        List<Physiotherapist> physio = userRepository.findPhysioByName(request.name());
        return physioToUserGetCoachesResponse(physio);
    }

    public List<GetPhysioResponse>  physioToUserGetCoachesResponse(List<Physiotherapist> physio) {
        return physio.stream()
                .map(GetPhysioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region GET COACHES
    @Override
    public List<GetCoachesResponse> getAllCoaches() {
        List<Coach> coaches = userRepository.findAllCoaches();
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllCoachesByIsRemote(GetCoachesByRemoteRequest request) {
        List<Coach> coaches = userRepository.findCoachesByIsRemote(request.is_remote());
        return coachesToUserGetCoachesResponse(coaches);

    }

    @Override
    public List<GetCoachesResponse> getAllCoachesBySpecialization(GetCoachesBySpecializationRequest request) {
        List<Coach> coaches = userRepository.findCoachesBySpecialization(request.specialization());
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllCoachesByName(GetCoachesByNameRequest request) {
        List<Coach> coaches = userRepository.findCoachesByName(request.name());
        return coachesToUserGetCoachesResponse(coaches);
    }

    public List<GetCoachesResponse>  coachesToUserGetCoachesResponse(List<Coach> coaches) {
        return coaches.stream()
                .map(GetCoachesResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region TRAINING SESSIONS

    @Override
    public List<GetTrainingSessionResponse> findAllTrainingSession() {
        List<TrainingSession> trainingSessions = userRepository.findAllTrainingSessions();
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByRecommendedLevel(request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByDuration(request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByName(GetTrainingSessionsByNameRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByName(request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request) {
        List<TrainingSession> trainingSessions = userRepository.findTrainingSessionsByCoachName(request.coach_name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    public List<GetTrainingSessionResponse>  trainingSessionToClientGetTrainingSessionResponse(List<TrainingSession> trainings) {
        return trainings.stream()
                .map(GetTrainingSessionResponse::fromEntity)
                .collect(Collectors.toList());
    }
    // endregion

}
