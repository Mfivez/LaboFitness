package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession.*;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.response.client.getTrainingSession.ClientGetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.service.ClientService;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl  implements ClientService {

    private final ClientRepository clientRepository;

    // region CLASSIC CRUD
    @Override
    public Client getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return List.of();
    }

    @Override
    public Client create(Client entity) {
        return null;
    }

    @Override
    public Client update(Client entity) {
        return null;
    }

    @Override
    public Client delete(Long aLong) {
        return null;
    }
    // endregion

    // region PERSONAL TRAINING SESSIONS
    @Override
    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByClientId(ClientGetPersonalTrainingSessionByClientIdRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByClientId(request.Id());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(ClientGetPersonalTrainingSessionByRecommendedLvlRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByRecommendedLevel(request.Id(), request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(ClientGetPersonalTrainingSessionsByDurationRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByDuration(request.Id(), request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByName(ClientGetPersonalTrainingSessionsByNameRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByName(request.Id(), request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(ClientGetPersonalTrainingSessionsByCoachNameRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByCoachName(request.Id(), request.coach_name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    // endregion

    // region TRAINING SESSIONS

    @Override
    public List<ClientGetTrainingSessionResponse> findAllTrainingSession() {
        List<TrainingSession> trainingSessions = clientRepository.findAllTrainingSessions();
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(ClientGetTrainingSessionByRecommendedLvlRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findTrainingSessionsByRecommendedLevel(request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findTrainingSessionByDuration(ClientGetTrainingSessionsByDurationRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findTrainingSessionsByDuration(request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findTrainingSessionByName(ClientGetTrainingSessionsByNameRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findTrainingSessionsByName(request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<ClientGetTrainingSessionResponse> findTrainingSessionByCoachName(ClientGetTrainingSessionsByCoachNameRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findTrainingSessionsByCoachName(request.coach_name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    public List<ClientGetTrainingSessionResponse>  trainingSessionToClientGetTrainingSessionResponse(List<TrainingSession> trainings) {
        return trainings.stream()
                .map(ClientGetTrainingSessionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion
}