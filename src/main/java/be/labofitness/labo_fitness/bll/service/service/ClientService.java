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
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Competition;
import be.labofitness.labo_fitness.domain.entity.TrainingSession;
import java.util.List;

/**
 * Service interface for managing {@link Client}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface ClientService  extends CrudService<Client, Long> {

    // region ACCOUNT MANAGEMENT

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
     * Subscribes a {@link Client} to a {@link TrainingSession}.
     *
     * @param request the request for subscription to a {@link TrainingSession}
     * @return the subscription response
     */
    TrainingSubscriptionResponse subscribeToOneTrainingSession(TrainingSubscriptionRequest request);

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
    List<PlanningResponse> getPlanning(ClientPlanningRequest request) ;

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

}
