package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionByClientIdRequest;
import be.labofitness.labo_fitness.bll.models.response.client.getTrainingSession.ClientGetTrainingSessionByClientIdResponse;
import be.labofitness.labo_fitness.bll.service.ClientService;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

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

    // region TRAINING SESSIONS
    @Override
    public List<ClientGetTrainingSessionByClientIdResponse> findClientTrainingSessionByClientId(ClientGetTrainingSessionByClientIdRequest request) {
        List<TrainingSession> trainingSessions = clientRepository.findTrainingSessionsByClientId(request.Id());
        return trainingSessionToClientGetTrainingSessionByClientIdResponse(trainingSessions);
    }

    public List<ClientGetTrainingSessionByClientIdResponse>  trainingSessionToClientGetTrainingSessionByClientIdResponse(List<TrainingSession> trainings) {
        return trainings.stream()
                .map(ClientGetTrainingSessionByClientIdResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // end region
}