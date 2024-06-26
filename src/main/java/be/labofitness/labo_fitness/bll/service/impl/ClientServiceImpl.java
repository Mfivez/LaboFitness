package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.AlreadyExistException;
import be.labofitness.labo_fitness.bll.exception.Unauthorize.UnauthorizedException;
import be.labofitness.labo_fitness.bll.exception.inscriptionClosed.EventCloseException;
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
import be.labofitness.labo_fitness.bll.service.service.*;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.entity.base.Address;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of the {@link ClientService} interface.
 * Provides CRUD operations for managing {@link Client} entities.
 */
@RequiredArgsConstructor
@Service
public class ClientServiceImpl  implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final SecurityService securityService;
    private final RoleService roleService;
    private final PlanningService planningService;
    private final CompetitionService competitionService;
    private final TrainingSessionService trainingService;
    private final PhysiotherapistService physiotherapistService;
    private final AppointmentService appointmentService;

    // region MANAGE ACCOUNT

    /**
     * Manages the account details of a {@link Client}.
     *
     * @param request the account management request
     * @return the account management response
     * @throws AlreadyExistException if the email already exists
     */
    @Override @Transactional
    public ClientManageAccountResponse manageAccount(ClientManageAccountRequest request) {
        String message  = "getCurrentMethodeName()";
        Client client = securityService.getAuthentication(Client.class);

        if (userService.emailUpdateIfValid(client.getEmail(), request.email())) {  client.setEmail(request.email());  }

        client.setName(request.name());
        client.setLastname(request.lastName());
        client.setGender(request.gender());
        client.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
        client.setWeight(request.weight());
        client.setHeight(request.height());
        clientRepository.save(client);

        return ClientManageAccountResponse.fromEntity(client,message);
    }

    //endregion

    // region AUTHENTICATE

    /**
     * Registers a new {@link Client}.
     *
     * @param request the registration request
     * @return the registration response
     * @throws AlreadyExistException if the email already exists
     */
    @Transactional @Override
    public RegisterResponse register(ClientRegisterRequest request) {

        if (userService.checkEmail(request.email())) {
            throw new AlreadyExistException("email already exists : " + request.email());  }

        Client client = new Client ();
                client.setWeight(request.weight());
                client.setHeight(request.height());
                client.setName(request.name());
                client.setLastname(request.lastName());
                client.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), LaboFitnessUtil.getMonthEnumFormat(request.month()) , request.day()));
                client.setEmail(request.email());
                client.setPassword(passwordEncoder.encode(request.password()));
                if (request.gender() != null) {  client.setGender(request.gender());  }
                client.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
                client.setRoles(roleService.setRole(Set.of("USER", "CLIENT")));
        clientRepository.save(client);

        return new RegisterResponse("Account created with success");
    }

    //endregion

    // region COMPETITION REGISTER

    /**
     * Registers the authenticated {@link Client} to a {@link Competition}.
     *
     * @param request the request containing the {@link Competition} name and start date
     * @return the {@link Competition} registration response
     */
    @Override @Transactional
    public CompetitionRegisterResponse registerToOneCompetition(CompetitionRegisterRequest request) {
        String message;

        Client client = securityService.getAuthentication(Client.class);

        Competition competition = competitionService.getCompetitionByCompetitionNameId(
                LaboFitnessUtil.CompetitionNameIdBuilder(
                        request.competitionName(),
                        request.startDate()
                ));

        if (client.getCompetitions().stream().anyMatch(competition1 -> competition1.getId().equals(competition.getId()))) {
            message = "You're already registered to the competition named : " + competition.getName() + " !";
        } else {
            client.setCompetitions(List.of(competition));
            clientRepository.save(client);

            message = "You've been register to the competition named " + competition.getName() +
                    " du " + LaboFitnessUtil.DateToStringFormatDayMonthValueYear(competition.getStartDate()) + " with success !";
        }

        return new CompetitionRegisterResponse(message);
    }

    // endregion

    // region TRAINING SUBSCRIPTION

    /**
     * Subscribes the authenticated {@link Client} to a {@link TrainingSession}.
     *
     * @param request the request containing the {@link TrainingSession} ID
     * @return the training subscription response
     */
    @Override @Transactional
    public TrainingSubscriptionResponse subscribeToOneTrainingSession(TrainingSubscriptionRequest request) {
        String message;
        Client client = securityService.getAuthentication(Client.class);
        TrainingSession training = trainingService.getOne(request.id());

        if (client.getTrainingSessions().stream().anyMatch(trainingSession -> training.getId().equals(trainingSession.getId()))) {
            message = "You're already registered to the training named : " + training.getName() + " !";
        }
        else if(!training.isInscriptionOpen()) {
            client.setTrainingSessions(List.of(training));
            clientRepository.save(client);

            message = "You've been register to the competition named " + training.getName() +
                    " du " + LaboFitnessUtil.DateToStringFormatDayMonthValueYear(training.getStartDate()) + " with success !";
            }
        else {
            throw new EventCloseException("Training session inscription closed");
        }

        return new TrainingSubscriptionResponse(message);
    }

    // endregion

    // region PLANNING

    /**
     * Retrieves the planning for the authenticated {@link Client}.
     *
     * @param request the request containing the planning details
     * @return the planning response
     */
    @Override
    public List<PlanningResponse> getPlanning(ClientPlanningRequest request) {
        return IntStream.range(0, getEventDetails(request, Competition::getStartDate, TrainingSession::getStartDate, Appointment::getStartDate).size())
                .mapToObj(i -> new PlanningResponse(
                        getEventDetails(request, Competition::getStartDate, TrainingSession::getStartDate, Appointment::getStartDate).get(i),
                        getEventDetails(request, Competition::getEndDate, TrainingSession::getEndDate, Appointment::getEndDate).get(i),
                        getEventDetails(request, Competition::getName, TrainingSession::getName, Appointment::getName).get(i)))
                .collect(Collectors.toList());
    }

    private <T> List<T> getEventDetails(ClientPlanningRequest request, Function<Competition, T> compMapper, Function<TrainingSession, T> trainMapper, Function<Appointment, T> appMapper) {
        List<T> details = new ArrayList<>();
        boolean includeAll = request.types() == null || request.types().isEmpty();
        boolean includeOnlyComp = request.sports() != null && !request.sports().isEmpty();
        boolean includeCoach = request.coachMail() != null;
        boolean includePhysio = request.physiotherapistMail() != null;

        if ((includeAll || request.types().contains("competition")) && !includePhysio) {
            List<T> compDetails = planningService.getAllClientCompetitions(request).stream()
                    .map(compMapper).toList();
            details.addAll(compDetails);
        }

        if (!includeOnlyComp && (includeAll || request.types().contains("appointment")) && !includeCoach) {
            List<T> appDetails = planningService.getAllClientAppointments(request).stream()
                    .map(appMapper).toList();
            details.addAll(appDetails);
        }

        if (!includeOnlyComp && (includeAll || request.types().contains("training")) && !includePhysio) {
            List<T> trainDetails = planningService.getAllClientTrainings(request).stream()
                    .map(trainMapper).toList();
            details.addAll(trainDetails);
        }

        return details;
    }

    // endregion

    // region MAKE APPOINTMENT

    /**
     * Makes a request for a new {@link Appointment} by a {@link Client} to a {@link Physiotherapist}.
     *
     * @param request the request for {@link Appointment}
     * @return the response indicating the status of the request
     * @throws IllegalStateException if there are too many pending {@link Appointment} requests or if the reason for the {@link Appointment} already exists
     */
    @Override @Transactional
    public MakeRequestForAppointmentResponse makeRequestForAppointment(MakeRequestForAppointmentRequest request) {
        if (appointmentService.getPendingRequestsCount(securityService.getAuthentication(Client.class)) >= 3) {
            throw new IllegalStateException("Too many pending appointment requests");
        }
        if (appointmentService.ExistByReason(securityService.getAuthentication(Client.class), request.reasonOfAppointment())) {
            throw new IllegalStateException("That pending appointment reason already exist");
        }

        appointmentService.create(
                new Appointment(
                        securityService.getAuthentication(Client.class),
                        physiotherapistService.getOneByEmail(request.physiotherapistEmail()),
                        request.reasonOfAppointment(),
                        AppointmentStatus.PENDING)
        );

        return new MakeRequestForAppointmentResponse("Request for appointment sent");
    }

    /**
     * Accepts or refuses an {@link Appointment} planning by the {@link Client}.
     *
     * @param request the request to accept or refuse {@link Appointment} planning
     * @return the response indicating the status of the acceptance/refusal
     * @throws IllegalStateException if the {@link Appointment} date is already passed or not yet defined
     */
    @Override @Transactional
    public AcceptAppointmentPlanningResponse acceptAppointmentPlanning(AcceptAppointmentPlanningRequest request) {
        String message ;
        Appointment appointment = clientRepository.getAppointmentById(request.id());

        if (LocalDateTime.now().isAfter(appointment.getStartDate())) {
            message = "You can't accept an appointment already passed";
            return new AcceptAppointmentPlanningResponse(message);
        }
        if (appointment.getStartDate() == null) {
            throw new IllegalStateException("Appointment date is not yet defined");
        }
        if (request.status() == AppointmentStatus.REFUSED) {
            appointment.setStartDate(null);
        }

        appointment.setAppointmentStatusResponseDescription(request.description());
        appointment.setAppointmentStatus(request.status());
        appointmentService.update(appointment);

        return new AcceptAppointmentPlanningResponse("Response to appointment sent to physiotherapist");
    }

    /**
     * Cancels an appointment by the {@link Client}..
     *
     * @param request the request to cancel the {@link Appointment}
     * @return the response indicating the status of the cancellation
     */
    @Override
    public CancelAppointmentResponse cancelAppointment(CancelAppointmentRequest request) {
        Appointment appointment = clientRepository.getAppointmentById( request.id() );
        String message ;

        if (request.isCancel()) {
            appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        }

        if (LocalDateTime.now().isAfter(appointment.getStartDate())){
            throw new UnauthorizedException("You can't cancel an appointment already passed: " + appointment.getStartDate());
        }

        if (!LocalDateTime.now().isAfter(appointment.getStartDate().minusDays(1))) {
            appointment.setPrice(0);
            message = "Appointment cancelled with success";
        }
        else {
            message = "You can cancel but you have to pay the price of the appointment : " + appointment.getPrice();
        }

        appointment.setCancel( request.isCancel() );
        appointmentService.update(appointment);

        return new CancelAppointmentResponse(message);
    }

    // endregion

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Client} by its ID.
     *
     * @param id the ID of the {@link Client} to retrieve
     * @return the {@link Client} with the given ID
     */
    @Override
    public Client getOne(Long id) {return null;}

    /**
     * Retrieves all {@link Client}.
     *
     * @return a list of all {@link Client}
     */
    @Override
    public List<Client> getAll() {return clientRepository.findAll();}

    /**
     * Creates a new {@link Client}.
     *
     * @param entity the {@link Client} to create
     * @return the created {@link Client}
     */
    @Override
    public Client create(Client entity) {return null;}

    /**
     * Updates an existing {@link Client}.
     *
     * @param entity the {@link Client} to update
     * @return the updated {@link Client}
     */
    @Override
    public Client update(Client entity) {return null;}

    /**
     * Deletes an {@link Client} by its ID.
     *
     * @param id the ID of the {@link Client} to delete
     * @return the deleted {@link Client}, or null if not found
     */
    @Override
    public Client delete(Long id) {return null;}

    // endregion

}