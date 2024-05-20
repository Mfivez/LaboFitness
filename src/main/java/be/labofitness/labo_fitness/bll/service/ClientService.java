package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.client.getPersonalTrainingSession.*;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.client.getTrainingSession.ClientGetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.client.registerClient.ClientRegisterRequest;
import be.labofitness.labo_fitness.bll.models.response.client.getTrainingSession.ClientGetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.models.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Client;


import java.util.List;

public interface ClientService  extends CrudService<Client, Long> {

    public RegisterResponse register(ClientRegisterRequest request);

    // region PERSONAL TRAINING SESSIONS

    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByClientId(ClientGetPersonalTrainingSessionByClientIdRequest request);

    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(ClientGetPersonalTrainingSessionByRecommendedLvlRequest request);

    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(ClientGetPersonalTrainingSessionsByDurationRequest request);

    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByName(ClientGetPersonalTrainingSessionsByNameRequest request);

    public List<ClientGetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(ClientGetPersonalTrainingSessionsByCoachNameRequest request);

    // endregion

    // region TRAINING SESSIONS

    public List<ClientGetTrainingSessionResponse> findAllTrainingSession();

    public List<ClientGetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(ClientGetTrainingSessionByRecommendedLvlRequest request);

    public List<ClientGetTrainingSessionResponse> findTrainingSessionByDuration(ClientGetTrainingSessionsByDurationRequest request);

    public List<ClientGetTrainingSessionResponse> findTrainingSessionByName(ClientGetTrainingSessionsByNameRequest request);

    public List<ClientGetTrainingSessionResponse> findTrainingSessionByCoachName(ClientGetTrainingSessionsByCoachNameRequest request);

    // endregion

}
