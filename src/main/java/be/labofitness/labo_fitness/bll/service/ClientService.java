package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionByClientIdRequest;
import be.labofitness.labo_fitness.bll.models.response.client.getTrainingSession.ClientGetTrainingSessionByClientIdResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Client;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface ClientService  extends CrudService<Client, Long> {

    // region TRAINING SESSIONS

    public List<ClientGetTrainingSessionByClientIdResponse> findClientTrainingSessionByClientId(ClientGetTrainingSessionByClientIdRequest request);

    // endregion
}
