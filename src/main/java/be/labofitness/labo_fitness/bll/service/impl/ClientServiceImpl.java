package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.exception.doesntExists.DoesntExistException;
import be.labofitness.labo_fitness.bll.exception.doesntExists.EmailDoesntExistException;
import be.labofitness.labo_fitness.bll.exception.inscriptionClosed.InscriptionCloseException;
import be.labofitness.labo_fitness.bll.exception.notMatching.PasswordNotMatchingException;
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
import be.labofitness.labo_fitness.bll.model.request.client.manageAccount.changePassword.ClientChangePasswordRequest;
import be.labofitness.labo_fitness.bll.model.response.client.manageAccount.changePassword.ClientChangePasswordResponse;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.*;
import be.labofitness.labo_fitness.bll.service.service.ClientService;
import be.labofitness.labo_fitness.bll.service.service.CompetitionService;
import be.labofitness.labo_fitness.bll.service.service.PlanningService;
import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import be.labofitness.labo_fitness.dal.repository.*;
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
import java.util.stream.Collectors;

//TODO REFAIRE METH
//import static be.labofitness.labo_fitness.il.utils.LaboFitnessUtil.getCurrentMethodeName;

/**
 * Implementation of the {@link ClientService} interface.
 * Provides CRUD operations for managing {@link Client} entities.
 */
@RequiredArgsConstructor
@Service
public class ClientServiceImpl  implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository; //TODO REFAC
    private final RoleRepository roleRepository; //TODO REFAC
    private final CompetitionService competitionService;
    private final TrainingSessionRepository trainingSessionRepository; //TODO REFAC
    private final PhysiotherapistRepository physiotherapistRepository; //TODO REFAC
    private final AppointmentRepository appointmentRepository; //TODO REFAC
    private final SecurityService securityService;
    private final RoleService roleService;
    private final PlanningService planningService;

    // region MANAGE ACCOUNT

    /**
     * Manages the account details of a {@link Client}.
     *
     * @param request the account management request
     * @return the account management response
     * @throws EmailAlreadyExistsException if the email already exists
     */
    @Override
    @Transactional
    public ClientManageAccountResponse manageAccount(ClientManageAccountRequest request) {
        String message  = "getCurrentMethodeName()";
        Client client = securityService.getAuthentication(Client.class);

        if (!client.getEmail().equals(request.email())) {
            if (!userRepository.existsByEmail(request.email())) {  client.setEmail(request.email());  }
            else{
                throw new PasswordNotMatchingException("Email already exists");
            }
        }

        client.setName(request.name());
        client.setLastname(request.lastName());
        client.setGender(request.gender());
        client.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
        client.setWeight(request.weight());
        client.setHeight(request.height());
        clientRepository.save(client);

        return ClientManageAccountResponse.fromEntity(client,message);
    }


    /**
     * Update the password of an {@link Client} account
     * @param request of the {@link ClientChangePasswordRequest} to update
     * @return response {@link ClientChangePasswordResponse} with a message
     */
    @Override
    @Transactional
    public ClientChangePasswordResponse changePassword(ClientChangePasswordRequest request) {

        String message = "getCurrentMethodeName()";

        Client client = securityService.getAuthentication(Client.class);

        if(!passwordEncoder.matches(request.oldPassword(), clientRepository.findPasswordByClientId(client.getId()))){

            throw new PasswordNotMatchingException("passwords are not matching");
        }
        client.setPassword(passwordEncoder.encode(request.newPassword()));
        clientRepository.save(client);

        return ClientChangePasswordResponse.fromEntity(client, message);
    }

    //endregion

    // region AUTHENTICATE

    /**
     * Registers a new {@link Client}.
     *
     * @param request the registration request
     * @return the registration response
     * @throws EmailAlreadyExistsException if the email already exists
     */
    @Transactional @Override
    public RegisterResponse register(ClientRegisterRequest request) {

        if(userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("email already exists : " + request.email());
        }

        Client client = new Client ();
                client.setWeight(request.weight());
                client.setHeight(request.height());
                client.setName(request.name());
                client.setLastname(request.lastName());
                client.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), request.month(), request.day()));
                client.setEmail(request.email());
                client.setPassword(passwordEncoder.encode(request.password()));
                client.setGender(request.gender());
                client.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
                client.setRoles(roleService.setRole(Set.of("USER", "CLIENT"), roleRepository));
        clientRepository.save(client);

        return new RegisterResponse("Account created with success");
    }

    //endregion

    // region PERSONAL TRAINING SESSIONS

    /**
     * Retrieves personal {@link TrainingSession} of a {@link Client}.
     *
     * @return list of personal {@link TrainingSession}
     */
    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSession() {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessions(client.getId());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves personal {@link TrainingSession} of a {@link Client} by recommended level.
     *
     * @param request the request to filter by recommended level
     * @return list of personal {@link TrainingSession}
     */
    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByRecommendedLevel(client.getId(), request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves personal {@link TrainingSession} of a {@link Client} by duration.
     *
     * @param request the request to filter by recommended level
     * @return list of personal {@link TrainingSession}
     */
    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByDuration(client.getId(), request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves personal {@link TrainingSession} of a {@link Client} by name.
     *
     * @param request the request to filter by recommended level
     * @return list of personal {@link TrainingSession}
     */
    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByName(GetTrainingSessionsByNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByName(client.getId(), request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Retrieves personal {@link TrainingSession} of a {@link Client} by {@link Coach} name.
     *
     * @param request the request to filter by recommended level
     * @return list of personal {@link TrainingSession}
     */
    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByCoachName(client.getId(), request.coach_name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    /**
     * Converts a list of {@link TrainingSession} entities to a list of {@link GetTrainingSessionResponse}.
     *
     * @param trainings the list of {@link TrainingSession} to convert
     * @return the list of {@link TrainingSession} responses
     */
    public List<GetTrainingSessionResponse>  trainingSessionToClientGetTrainingSessionResponse(List<TrainingSession> trainings) {
        return trainings.stream()
                .map(GetTrainingSessionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // endregion

    // region GET PERSONAL PHYSIOTHERAPIST

    /**
     * Retrieves all personal {@link Physiotherapist} of the authenticated {@link Client}.
     *
     * @return the list of personal {@link Physiotherapist}
     */
    @Override
    public List<GetPhysioResponse> getAllPersonalPhysio() {
        Client client = securityService.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findAllPersonalPhysio(client.getId());
        return physioToUserGetCoachesResponse(physio);
    }

    /**
     * Retrieves personal {@link Physiotherapist} of the authenticated {@link Client} by specialization.
     *
     * @param request the request containing the specialization filter
     * @return the list of personal {@link Physiotherapist} matching the specialization
     */
    @Override
    public List<GetPhysioResponse> getPersonalPhysioBySpecialization(GetPhysioBySpecializationRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findPersonalPhysioBySpecialization(client.getId(), request.specialization());
        return physioToUserGetCoachesResponse(physio);
    }

    /**
     * Retrieves personal {@link Physiotherapist} of the authenticated {@link Client} by name.
     *
     * @param request the request containing the name filter
     * @return the list of personal {@link Physiotherapist} matching the name
     */
    @Override
    public List<GetPhysioResponse> getPersonalPhysioByName(GetPhysioByNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findPersonalPhysioByName(client.getId(), request.name());
        return physioToUserGetCoachesResponse(physio);
    }

    /**
     * Converts a list of {@link Physiotherapist} entities to a list of {@link GetPhysioResponse}.
     *
     * @param physio the list of {@link Physiotherapist} to convert
     * @return the list of {@link Physiotherapist} responses
     */
    public List<GetPhysioResponse>  physioToUserGetCoachesResponse(List<Physiotherapist> physio) {
        return physio.stream()
                .map(GetPhysioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region GET COACHES

    /**
     * Retrieves all personal {@link Coach} of the authenticated {@link Client}.
     *
     * @return the list of personal {@link Coach}
     */
    @Override
    public List<GetCoachesResponse> getAllPersonalCoaches() {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findAllPersonalCoaches(client.getId());
        return coachesToUserGetCoachesResponse(coaches);
    }

    /**
     * Retrieves all personal {@link Coach} of the authenticated {@link Client} by remote availability.
     *
     * @param request the request containing the remote availability filter
     * @return the list of personal {@link Coach} matching the remote availability
     */
    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesByIsRemote(GetCoachesByRemoteRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesByIsRemote(client.getId(), request.is_remote());
        return coachesToUserGetCoachesResponse(coaches);

    }

    /**
     * Retrieves all personal {@link Coach} of the authenticated {@link Client} by specialization.
     *
     * @param request the request containing the specialization filter
     * @return the list of personal {@link Coach} matching the specialization
     */
    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesBySpecialization(GetCoachesBySpecializationRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesBySpecialization(client.getId(), request.specialization());
        return coachesToUserGetCoachesResponse(coaches);
    }

    /**
     * Retrieves all personal {@link Coach} of the authenticated {@link Client} by name.
     *
     * @param request the request containing the name filter
     * @return the list of personal {@link Coach} matching the name
     */
    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesByName(GetCoachesByNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesByName(client.getId(), request.name());
        return coachesToUserGetCoachesResponse(coaches);
    }

    /**
     * Converts a list of {@link Coach} entities to a list of {@link GetCoachesResponse}.
     *
     * @param coaches the list of {@link Coach} to convert
     * @return the list of {@link Coach} responses
     */
    public List<GetCoachesResponse>  coachesToUserGetCoachesResponse(List<Coach> coaches) {
        return coaches.stream()
                .map(GetCoachesResponse::fromEntity)
                .collect(Collectors.toList());
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

        TrainingSession training = trainingSessionRepository.findTrainingSessionById(request.id()).orElseThrow(
                () -> new DoesntExistException("Training not found")
        );

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
            throw new InscriptionCloseException("Training session inscription closed");
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
    public PlanningResponse getPlanning(ClientPlanningRequest request) {
        return new PlanningResponse(
                getStartDates(request),
                getEndDates(request),
                getEventName(request)
        );
    }

    /**
     * Retrieves the start dates of events based on the {@link Client} planning request.
     *
     * @param request the {@link Client} planning request
     * @return the list of start dates
     */
    private List<LocalDateTime> getStartDates(ClientPlanningRequest request) {
        List<LocalDateTime> startDate = iniDate();
        boolean[] booleans = getEndDatesBoolean(request.types(), request.sports(), request.coachMail(), request.physiotherapistMail());
        boolean includeAll = booleans[0];
        boolean includeOnlyComp = booleans[1];
        boolean includeCoach = booleans[2];
        boolean includePhysio = booleans[3];

        if ((includeAll || request.types().contains("competition")) && !includePhysio) {
            List<LocalDateTime> compStartDates = planningService.getAllClientCompetitions(request).stream()
                    .map(Competition::getStartDate)
                    .collect(Collectors.toList());
            startDate.addAll(compStartDates);
        }

        if (!includeOnlyComp && (includeAll || request.types().contains("appointment")) && !includeCoach) {
            List<LocalDateTime> appStartDates = planningService.getAllClientAppointments(request).stream()
                    .map(Appointment::getStartDate)
                    .collect(Collectors.toList());
            startDate.addAll(appStartDates);
        }

        if (!includeOnlyComp && (includeAll || request.types().contains("training")) && !includePhysio) {
            List<LocalDateTime> trainingStartDates = planningService.getAllClientTrainings(request).stream()
                    .map(TrainingSession::getStartDate)
                    .collect(Collectors.toList());
            startDate.addAll(trainingStartDates);
        }

        return startDate;
    }

    /**
     * Retrieves the end dates of events based on the {@link Client} planning request.
     *
     * @param request the {@link Client} planning request
     * @return the list of end dates
     */
    private List<LocalDateTime> getEndDates(ClientPlanningRequest request) {
        List<LocalDateTime> endDate = iniDate();
        boolean[] booleans = getEndDatesBoolean(request.types(), request.sports(), request.coachMail(), request.physiotherapistMail());
        boolean includeAll = booleans[0];
        boolean includeOnlyComp = booleans[1];
        boolean includeCoach = booleans[2];
        boolean includePhysio = booleans[3];

        if ( !includeOnlyComp && (includeAll || request.types().contains("appointment")) && !includeCoach) {
            List<LocalDateTime> appointmentEndDate = planningService.getAllClientAppointments(request).stream()
                    .map(Appointment::getEndDate)
                    .collect(Collectors.toList());
            endDate.addAll(appointmentEndDate);
        }

        if ((includeAll || request.types().contains("competition")) && !includePhysio) {
            List<LocalDateTime> compEndDates = planningService.getAllClientCompetitions(request).stream()
                    .map(Competition::getEndDate)
                    .collect(Collectors.toList());
            endDate.addAll(compEndDates);
        }

        if (!includeOnlyComp && (includeAll || request.types().contains("training")) && !includePhysio) {
            List<LocalDateTime> trainingEndDates = planningService.getAllClientTrainings(request).stream()
                    .map(TrainingSession::getEndDate)
                    .collect(Collectors.toList());
            endDate.addAll(trainingEndDates);
        }

        return endDate;
    }

    /**
     * Retrieves the names of events based on the {@link Client} planning request.
     *
     * @param request the {@link Client} planning request
     * @return the list of event names
     */
    private List<String> getEventName(ClientPlanningRequest request) {
        List<String> names = new ArrayList<>();
        boolean[] booleans = getEndDatesBoolean(request.types(), request.sports(), request.coachMail(), request.physiotherapistMail());
        boolean includeAll = booleans[0];
        boolean includeOnlyComp = booleans[1];
        boolean includeCoach = booleans[2];
        boolean includePhysio = booleans[3];

        if (!includeOnlyComp && (includeAll || request.types().contains("appointment")) && !includeCoach) {
            List<String> appointmentNames = planningService.getAllClientAppointments(request).stream()
                    .map(Appointment::getName)
                    .collect(Collectors.toList());
            names.addAll(appointmentNames);
        }

        if ((includeAll || request.types().contains("competition")) && !includePhysio) {
            List<String> compNames = planningService.getAllClientCompetitions(request).stream()
                    .map(Competition::getName)
                    .collect(Collectors.toList());
            names.addAll(compNames);
        }

        if (!includeOnlyComp && (includeAll || request.types().contains("training")) && !includePhysio) {
            List<String> trainingNames = planningService.getAllClientTrainings(request).stream()
                    .map(TrainingSession::getName)
                    .collect(Collectors.toList());
            names.addAll(trainingNames);
        }
        return names;
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
        Client client = securityService.getAuthentication(Client.class);
        Physiotherapist physiotherapist = physiotherapistRepository.findByEmail(request.physiotherapistEmail())
                .orElseThrow(() -> new EmailDoesntExistException(
                        "Email not found"));

        long pendingRequestsCount = appointmentRepository.countByClientAndAppointmentStatus(client, AppointmentStatus.PENDING);

        if (pendingRequestsCount >= 3) {
            throw new IllegalStateException("Too many pending appointment requests");
        }

        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setReasonOfAppointment(request.reasonOfAppointment());

        boolean duplicateReasonExists = appointmentRepository.existsByClientAndReasonOfAppointmentAndAppointmentStatus(
                client,
                request.reasonOfAppointment(),
                AppointmentStatus.PENDING);

        if (duplicateReasonExists) {
            throw new IllegalStateException("That pending appointment reason already exist");
        }
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);
        appointment.setPhysiotherapist(physiotherapist);
        appointmentRepository.save(appointment);

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
        Appointment appointment = clientRepository.getAppointmentById(  request.id()  );
        String message ;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentDate = appointment.getStartDate();
        appointment.setAppointmentStatusResponseDescription(request.description());
        if (now.isAfter(appointmentDate)) {
            message = "You can't accept an appointment already passed";
            return new AcceptAppointmentPlanningResponse(message);
        }
        if (appointment.getStartDate() == null) {
            throw new IllegalStateException("Appointment date is not yet defined");
        }
        appointment.setAppointmentStatus(request.status());
        if (request.status() == AppointmentStatus.REFUSED) {
            appointment.setStartDate(null);
        }
        appointmentRepository.save(appointment);

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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentDate = appointment.getStartDate();
        if (now.isAfter(appointmentDate)){
            message = "You can't cancel an appointment already passed";
            return new CancelAppointmentResponse(message);
        }
        if (!now.isAfter(appointmentDate.minusDays(1))) {
            appointment.setPrice(0);
            message = "Appointment cancelled with success";
        }
        else {
            message = "You can cancel but you have to pay the price of the appointment : " + appointment.getPrice();
        }
        appointment.setCancel( request.isCancel() );
        if (request.isCancel()) {
            appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        }
        appointmentRepository.save(appointment);

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
    public Client getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Client}.
     *
     * @return a list of all {@link Client}
     */
    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    /**
     * Creates a new {@link Client}.
     *
     * @param entity the {@link Client} to create
     * @return the created {@link Client}
     */
    @Override
    public Client create(Client entity) {
        return null;
    }

    /**
     * Updates an existing {@link Client}.
     *
     * @param entity the {@link Client} to update
     * @return the updated {@link Client}
     */
    @Override
    public Client update(Client entity) {
        return null;
    }

    /**
     * Deletes an {@link Client} by its ID.
     *
     * @param id the ID of the {@link Client} to delete
     * @return the deleted {@link Client}, or null if not found
     */
    @Override
    public Client delete(Long id) {
        return null;
    }

    // endregion

    // region UTILS

    /**
     * Initializes and returns a new list of {@link LocalDateTime}.
     *
     * @return an empty list of {@link LocalDateTime}
     */
    private List<LocalDateTime> iniDate() {
        return new ArrayList<>();
    }

    /**
     * Determines {@code boolean} values based on the presence of various parameters.
     *
     * @param types a list of types to check
     * @param sports a list of sports to check
     * @param coachMail the email of the coach
     * @param physiotherapistMail the email of the physiotherapist
     * @return a boolean array where:
     *         <ul>
     *           <li>{@code booleans}[0] is true if {@code types} is null or empty (include all)</li>
     *           <li>{@code booleans}[1] is true if {@code sports} is not null and not empty (include {@link Competition} only)</li>
     *           <li>{@code booleans}[2] is true if {@code coachMail} is not null (include {@link Coach)</li>
     *           <li>{@code booleans}[3] is true if {@code physiotherapistMail} is not null (include {@link Physiotherapist)</li>
     *         </ul>
     */
    private boolean[] getEndDatesBoolean(List<String>  types, List<String> sports, String coachMail, String physiotherapistMail) {
        boolean[] booleans = new boolean[4];
        booleans[0] = types == null || types.isEmpty(); // INCLUDE ALL
        booleans[1] = sports != null && !sports.isEmpty(); // INCLUDE COMPETITION ONLY
        booleans[2] = coachMail != null; // INCLUDE COACH
        booleans[3] = physiotherapistMail != null; // INCLUDE PHYSIO
        return booleans;
    }

    // endregion

}