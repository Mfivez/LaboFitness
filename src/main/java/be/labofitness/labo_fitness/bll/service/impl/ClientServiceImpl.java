package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.exception.doesntExists.DoesntExistException;
import be.labofitness.labo_fitness.bll.exception.doesntExists.EmailDoesntExistException;
import be.labofitness.labo_fitness.bll.exception.inscriptionClosed.InscriptionCloseException;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterRequest;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionRequest;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.AcceptAppointmentPlanningRequest;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.CancelAppointmentRequest;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.MakeRequestForAppointmentRequest;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountRequest;
import be.labofitness.labo_fitness.bll.model.planning.ClientPlanningRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.model.register.ClientRegisterRequest;
import be.labofitness.labo_fitness.bll.model.client.CompetitionRegister.CompetitionRegisterResponse;
import be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription.TrainingSubscriptionResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.AcceptAppointmentPlanningResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.CancelAppointmentResponse;
import be.labofitness.labo_fitness.bll.model.client.makeAppointment.MakeRequestForAppointmentResponse;
import be.labofitness.labo_fitness.bll.model.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.model.planning.PlanningResponse;
import be.labofitness.labo_fitness.bll.model.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.model.register.RegisterResponse;
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


    // region AUTHENTICATE

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

    @Override @Transactional
    public ClientManageAccountResponse manageAccount(ClientManageAccountRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw  new EmailAlreadyExistsException("Email already exists :" + request.email()); }

        Client client = securityService.getAuthentication(Client.class);
        client.setName(request.name());
        client.setLastname(request.lastName());
        client.setEmail(request.email());
        client.setGender(request.gender());
        client.setAddress(new Address(request.street(), request.number(), request.city(), request.zipCode()));
        client.setWeight(request.weight());
        client.setHeight(request.height());
        clientRepository.save(client);

        return new ClientManageAccountResponse("Account modified with success");
    }

    //endregion

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

    // region PERSONAL TRAINING SESSIONS

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSession() {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessions(client.getId());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByRecommendedLevel(client.getId(), request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByDuration(client.getId(), request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByName(GetTrainingSessionsByNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByName(client.getId(), request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByCoachName(client.getId(), request.coach_name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    public List<GetTrainingSessionResponse>  trainingSessionToClientGetTrainingSessionResponse(List<TrainingSession> trainings) {
        return trainings.stream()
                .map(GetTrainingSessionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // endregion

    // region GET PERSONAL PHYSIOTHERAPIST

    @Override
    public List<GetPhysioResponse> getAllPersonalPhysio() {
        Client client = securityService.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findAllPersonalPhysio(client.getId());
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPersonalPhysioBySpecialization(GetPhysioBySpecializationRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findPersonalPhysioBySpecialization(client.getId(), request.specialization());
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPersonalPhysioByName(GetPhysioByNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findPersonalPhysioByName(client.getId(), request.name());
        return physioToUserGetCoachesResponse(physio);
    }

    public List<GetPhysioResponse>  physioToUserGetCoachesResponse(List<Physiotherapist> physio) {
        return physio.stream()
                .map(GetPhysioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region GET COACHES

    @Override
    public List<GetCoachesResponse> getAllPersonalCoaches() {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findAllPersonalCoaches(client.getId());
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesByIsRemote(GetCoachesByRemoteRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesByIsRemote(client.getId(), request.is_remote());
        return coachesToUserGetCoachesResponse(coaches);

    }

    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesBySpecialization(GetCoachesBySpecializationRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesBySpecialization(client.getId(), request.specialization());
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesByName(GetCoachesByNameRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesByName(client.getId(), request.name());
        return coachesToUserGetCoachesResponse(coaches);
    }

    public List<GetCoachesResponse>  coachesToUserGetCoachesResponse(List<Coach> coaches) {
        return coaches.stream()
                .map(GetCoachesResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //endregion

    // region COMPETITION REGISTER

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

    @Override
    public PlanningResponse getPlanning(ClientPlanningRequest request) {
        return new PlanningResponse(
                getStartDates(request),
                getEndDates(request),
                getEventName(request)
        );
    }

    private List<LocalDateTime> getStartDates(ClientPlanningRequest request) {
        List<LocalDateTime> startDate = new ArrayList<>();
        boolean includeAll = request.types() == null || request.types().isEmpty();
        boolean includeOnlyComp = request.sports() != null && !request.sports().isEmpty();
        boolean includeCoach = request.coachMail() != null;
        boolean includePhysio = request.physiotherapistMail() != null;

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

    private List<LocalDateTime> getEndDates(ClientPlanningRequest request) {
        List<LocalDateTime> endDate = new ArrayList<>();
        boolean includeAll = request.types() == null || request.types().isEmpty();
        boolean includeOnlyComp = request.sports() != null && !request.sports().isEmpty();
        boolean includeCoach = request.coachMail() != null;
        boolean includePhysio = request.physiotherapistMail() != null;

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

    private List<String> getEventName(ClientPlanningRequest request) {
        List<String> names = new ArrayList<>();
        boolean includeAll = request.types() == null || request.types().isEmpty();
        boolean includeOnlyComp = request.sports() != null && !request.sports().isEmpty();
        boolean includeCoach = request.coachMail() != null;
        boolean includePhysio = request.physiotherapistMail() != null;

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

    // Client-Kiné-Raison du RDV
    @Override @Transactional
    public MakeRequestForAppointmentResponse makeRequestForAppointment(MakeRequestForAppointmentRequest request) {
        Client client = securityService.getAuthentication(Client.class);
        Physiotherapist physiotherapist = physiotherapistRepository.findByEmail(request.physiotherapistEmail())
                .orElseThrow(() -> new EmailDoesntExistException(
                        "Email not found"));

    //Verifier le nombre de demandes en attente
        long pendingRequestsCount = appointmentRepository.countByClientAndAppointmentStatus(client, AppointmentStatus.PENDING);

        // Limiter a 3 appointments par client
        int maxPendingRequests = 3;

        if (pendingRequestsCount >= maxPendingRequests) {
            throw new IllegalStateException("Too many pending appointment requests");
        }

        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setReasonOfAppointment(request.reasonOfAppointment());
        // Verifier Reason of appointment
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

    @Override
    public List<Appointment> getAllAppointments() {
        return List.of();
    }

    // endregion


}