package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterRequest;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterResponse;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionRequest;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.*;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.model.register.ClientRegisterRequest;
import be.labofitness.labo_fitness.bll.model.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.changePassword.ClientChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.changePassword.ClientChangePasswordResponse;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.*;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

import java.util.List;

/**
 * Service interface for managing {@link Client}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface ClientService  extends CrudService<Client, Long> {

    // region ACCOUNT MANAGEMENT

    /**
     * Changes the password of a {@link Client}.
     *
     * @param request the {@link Client} change password request
     * @return the {@link Client} change password response
     */
    ClientChangePasswordResponse changePassword (ClientChangePasswordRequest request);

    /**
     * Registers a new {@link Client}.
     *
     * @param request the {@link Client} register request
     * @return the register response
     */
    RegisterResponse register(ClientRegisterRequest request);

    /**
     * Manages the account of a {@link Client}.
     *
     * @param request the {@link Client} manage account request
     * @return the {@link Client} manage account response
     */
    ClientManageAccountResponse manageAccount(ClientManageAccountRequest request);

    // endregion

    // region PERSONAL TRAINING SESSIONS

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client}.
     *
     * @return a list of personal {@link TrainingSession}
     */
    List<GetTrainingSessionResponse> findPersonalClientTrainingSession();

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on recommended level.
     *
     * @param request the request containing the recommended level criteria
     * @return a list of personal {@link TrainingSession} matching the recommended level criteria
     */
    List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request );

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on duration.
     *
     * @param request the request containing the duration criteria
     * @return a list of personal {@link TrainingSession} matching the duration criteria
     */
    List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request);

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on name.
     *
     * @param request the request containing the name criteria
     * @return a list of personal {@link TrainingSession} matching the name criteria
     */
    List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByName(GetTrainingSessionsByNameRequest request);

    /**
     * Retrieves personal {@link TrainingSession} for a {@link Client} based on coach name.
     *
     * @param request the request containing the coach name criteria
     * @return a list of personal {@link TrainingSession} matching the coach name criteria
     */
    List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request);

    /**
     * Subscribes a {@link Client} to a {@link TrainingSession}.
     *
     * @param request the request for subscription to a {@link TrainingSession}
     * @return the subscription response
     */
    TrainingSubscriptionResponse subscribeToOneTrainingSession(TrainingSubscriptionRequest request);

    // endregion

    //region PERSONAL PHYSIOTHERAPIST

    /**
     * Retrieves all personal {@link Physiotherapist}.
     *
     * @return a list of all personal {@link Physiotherapist}
     */
    List<GetPhysioResponse> getAllPersonalPhysio();

    /**
     * Retrieves personal {@link Physiotherapist} for a {@link Client} based on specialization.
     *
     * @param request the request containing the specialization criteria
     * @return a list of personal {@link Physiotherapist} matching the specialization criteria
     */
    List<GetPhysioResponse> getPersonalPhysioBySpecialization(GetPhysioBySpecializationRequest request);

    /**
     * Retrieves personal {@link Physiotherapist}  for a {@link Client} based on name.
     *
     * @param request the request containing the name criteria
     * @return a list of personal {@link Physiotherapist}  matching the name criteria
     */
    List<GetPhysioResponse> getPersonalPhysioByName(GetPhysioByNameRequest request);

    //endregion

    //region COACH

    /**
     * Retrieves all personal {@link Coach}.
     *
     * @return a list of all personal {@link Coach}
     */
    List<GetCoachesResponse> getAllPersonalCoaches();

    /**
     * Retrieves personal {@link Coach} for a {@link Client} based on the option of remote coaching.
     *
     * @param request the request containing the criteria for remote coaching option
     * @return a list of personal {@link Coach} matching the remote coaching option criteria
     */
    List<GetCoachesResponse> getAllPersonalCoachesByIsRemote(GetCoachesByRemoteRequest request);

    /**
     * Retrieves personal {@link Coach} for a {@link Client} based on specialization.
     *
     * @param request the request containing the specialization criteria
     * @return a list of personal {@link Coach} matching the specialization criteria
     */
    List<GetCoachesResponse> getAllPersonalCoachesBySpecialization(GetCoachesBySpecializationRequest request);

    /**
     * Retrieves personal {@link Coach} for a {@link Client} based on name.
     *
     * @param request the request containing the name criteria
     * @return a list of personal {@link Coach} matching the name criteria
     */
    List<GetCoachesResponse> getAllPersonalCoachesByName(GetCoachesByNameRequest request);

    // endregion

    // region REGISTER COMPETITION

    /**
     * Registers a {@link Client} to a {@link Competition}.
     *
     * @param request the {@link Competition} register request
     * @return the {@link Competition} register response
     */
    CompetitionRegisterResponse registerToOneCompetition(CompetitionRegisterRequest request);

    // endregion

    // region PLANNING

    /**
     * Retrieves the planning for a {@link Client}.
     *
     * @param request the {@link Client} planning request
     * @return the planning response
     */
    PlanningResponse getPlanning(ClientPlanningRequest request) ;

    // endregion

    // region MAKE APPOINTMENT

    /**
     * Makes a request for a new {@link Appointment}.
     *
     * @param request the request containing the details of the {@link Appointment}
     * @return the response indicating the outcome of the {@link Appointment} request
     */
    MakeRequestForAppointmentResponse makeRequestForAppointment(MakeRequestForAppointmentRequest request);

    /**
     * Accepts an {@link Appointment} planning request.
     *
     * @param request the request containing the details of the {@link Appointment} to accept
     * @return the response indicating the outcome of the {@link Appointment} acceptance
     */
    AcceptAppointmentPlanningResponse acceptAppointmentPlanning(AcceptAppointmentPlanningRequest request);

    /**
     * Cancels an existing {@link Appointment}.
     *
     * @param request the request containing the details of the {@link Appointment} to cancel
     * @return the response indicating the outcome of the {@link Appointment} cancellation
     */
    CancelAppointmentResponse cancelAppointment(CancelAppointmentRequest request);

    // endregion

    //region CRUD SERVICE

    Client getOneByEmail(String email);

    //endregion

}
