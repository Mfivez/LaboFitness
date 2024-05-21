package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.client.manageAccount.ClientManageAccountRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.client.registerClient.ClientRegisterRequest;
import be.labofitness.labo_fitness.bll.models.response.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.models.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Client;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface ClientService  extends CrudService<Client, Long> {


    // region ACCOUNT MANAGEMENT

    public RegisterResponse register(ClientRegisterRequest request);

    public ClientManageAccountResponse manageAccount(Authentication authentication, ClientManageAccountRequest request);

    // endregion

    // region PERSONAL TRAINING SESSIONS

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSession(Authentication authentication);

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(Authentication authentication, GetTrainingSessionByRecommendedLvlRequest request );

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(Authentication authentication, GetTrainingSessionsByDurationRequest request);

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByName(Authentication authentication, GetTrainingSessionsByNameRequest request);

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(Authentication authentication, GetTrainingSessionsByCoachNameRequest request);

    // endregion

    //region PERSONAL PHYSIOTHERAPIST
    List<GetPhysioResponse> getAllPersonalPhysio(Authentication authentication);

    List<GetPhysioResponse> getPersonalPhysioBySpecialization(Authentication authentication, GetPhysioBySpecializationRequest request);

    List<GetPhysioResponse> getPersonalPhysioByName(Authentication authentication, GetPhysioByNameRequest request);
    //endregion

    //region COACH
    List<GetCoachesResponse> getAllPersonalCoaches(Authentication authentication);

    List<GetCoachesResponse> getAllPersonalCoachesByIsRemote(Authentication authentication, GetCoachesByRemoteRequest request);

    List<GetCoachesResponse> getAllPersonalCoachesBySpecialization(Authentication authentication, GetCoachesBySpecializationRequest request);

    List<GetCoachesResponse> getAllPersonalCoachesByName(Authentication authentication, GetCoachesByNameRequest request);

    // endregion

}
