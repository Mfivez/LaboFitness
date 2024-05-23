package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.client.CompetitionRegister.CompetitionRegisterRequest;
import be.labofitness.labo_fitness.bll.models.request.client.TrainingSessionSubscription.TrainingSuscribRequest;
import be.labofitness.labo_fitness.bll.models.request.client.makeAppointment.AcceptAppointmentPlanningRequest;
import be.labofitness.labo_fitness.bll.models.request.client.makeAppointment.CancelAppointmentRequest;
import be.labofitness.labo_fitness.bll.models.request.client.makeAppointment.MakeRequestForAppointmentRequest;
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
import be.labofitness.labo_fitness.bll.models.response.client.CompetitionRegister.CompetitionRegisterResponse;
import be.labofitness.labo_fitness.bll.models.response.client.TrainingSessionSubscription.TrainingSuscribResponse;
import be.labofitness.labo_fitness.bll.models.response.client.makeAppointment.AcceptAppointmentPlanningResponse;
import be.labofitness.labo_fitness.bll.models.response.client.makeAppointment.CancelAppointmentResponse;
import be.labofitness.labo_fitness.bll.models.response.client.makeAppointment.MakeRequestForAppointmentResponse;
import be.labofitness.labo_fitness.bll.models.response.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.models.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface ClientService  extends CrudService<Client, Long> {


    // region ACCOUNT MANAGEMENT

    public RegisterResponse register(ClientRegisterRequest request);

    public ClientManageAccountResponse manageAccount(ClientManageAccountRequest request);

    // endregion

    // region PERSONAL TRAINING SESSIONS

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSession();

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request );

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request);

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByName(GetTrainingSessionsByNameRequest request);

    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request);

    // endregion

    //region PERSONAL PHYSIOTHERAPIST
    List<GetPhysioResponse> getAllPersonalPhysio();

    List<GetPhysioResponse> getPersonalPhysioBySpecialization(GetPhysioBySpecializationRequest request);

    List<GetPhysioResponse> getPersonalPhysioByName(GetPhysioByNameRequest request);
    //endregion

    //region COACH
    List<GetCoachesResponse> getAllPersonalCoaches();

    List<GetCoachesResponse> getAllPersonalCoachesByIsRemote(GetCoachesByRemoteRequest request);

    List<GetCoachesResponse> getAllPersonalCoachesBySpecialization(GetCoachesBySpecializationRequest request);

    List<GetCoachesResponse> getAllPersonalCoachesByName(GetCoachesByNameRequest request);

    // endregion

    // region REGISTER COMPETITION

    CompetitionRegisterResponse registerToOneCompetition(CompetitionRegisterRequest request);

    // endregion

    // region TRAINING SUBSCRIPTION

    TrainingSuscribResponse subscribeToOneTrainingSession(TrainingSuscribRequest request);

    // endregion

    // region MAKE APPOINTMENT

    MakeRequestForAppointmentResponse makeRequestForAppointment(MakeRequestForAppointmentRequest request);

    AcceptAppointmentPlanningResponse acceptAppointmentPlanning(AcceptAppointmentPlanningRequest request);

    CancelAppointmentResponse cancelAppointment(CancelAppointmentRequest request);

    // endregion

    // region CRUD

    List<Appointment> getAllAppointments();

    //endregion

}
