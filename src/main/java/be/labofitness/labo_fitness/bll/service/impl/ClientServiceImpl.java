package be.labofitness.labo_fitness.bll.service.impl;


import be.labofitness.labo_fitness.bll.exception.alreadyExists.EmailAlreadyExistsException;
import be.labofitness.labo_fitness.bll.exception.doesntExists.DoesntExistException;
import be.labofitness.labo_fitness.bll.exception.inscriptionClosed.InscriptionCloseException;
import be.labofitness.labo_fitness.bll.models.request.client.CompetitionRegister.CompetitionRegisterRequest;
import be.labofitness.labo_fitness.bll.models.request.client.TrainingSessionSubscription.TrainingSuscribRequest;
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
import be.labofitness.labo_fitness.bll.models.response.client.manageAccount.ClientManageAccountResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.models.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.ClientService;
import be.labofitness.labo_fitness.bll.service.CompetitionService;
import be.labofitness.labo_fitness.dal.repository.*;
import be.labofitness.labo_fitness.domain.entity.*;
import be.labofitness.labo_fitness.domain.entity.base.Adress;
import be.labofitness.labo_fitness.il.utils.LaboFitnessUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl  implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CompetitionService competitionService;
    private final CompetitionRepository competitionRepository;
    private final TrainingSessionRepository trainingSessionRepository;


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
                client.setLast_name(request.lastName());
                client.setBirthdate(LaboFitnessUtil.createNewDate(request.year(), request.month(), request.day()));
                client.setEmail(request.email());
                client.setPassword(passwordEncoder.encode(request.password()));
                client.setGender(request.gender());
                client.setAdress(new Adress(request.street(), request.number(), request.city(), request.zipCode()));
                client.setRoles(LaboFitnessUtil.setRole(Set.of("USER", "CLIENT"), roleRepository));
        clientRepository.save(client);

        return new RegisterResponse("Account created with success");
    }

    @Override @Transactional
    public ClientManageAccountResponse manageAccount(ClientManageAccountRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw  new EmailAlreadyExistsException("Email already exists :" + request.email()); }

        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        client.setName(request.name());
        client.setLast_name(request.lastName());
        client.setEmail(request.email());
        client.setGender(request.gender());
        client.setAdress(new Adress(request.street(), request.number(), request.city(), request.zipCode()));
        client.setWeight(request.weight());
        client.setHeight(request.height());
        clientRepository.save(client);

        return new ClientManageAccountResponse("Account modified with success");
    }

    //endregion

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

    // region PERSONAL TRAINING SESSIONS

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSession() {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessions(client.getId());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByRecommendedLevel(client.getId(), request.recommendedLevel());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByDuration(client.getId(), request.duration());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByName(GetTrainingSessionsByNameRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<TrainingSession> trainingSessions = clientRepository.findPersonalTrainingSessionsByName(client.getId(), request.name());
        return trainingSessionToClientGetTrainingSessionResponse(trainingSessions);
    }

    @Override
    public List<GetTrainingSessionResponse> findPersonalClientTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
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
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findAllPersonalPhysio(client.getId());
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPersonalPhysioBySpecialization(GetPhysioBySpecializationRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<Physiotherapist> physio = clientRepository.findPersonalPhysioBySpecialization(client.getId(), request.specialization());
        return physioToUserGetCoachesResponse(physio);
    }

    @Override
    public List<GetPhysioResponse> getPersonalPhysioByName(GetPhysioByNameRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
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
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findAllPersonalCoaches(client.getId());
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesByIsRemote(GetCoachesByRemoteRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesByIsRemote(client.getId(), request.is_remote());
        return coachesToUserGetCoachesResponse(coaches);

    }

    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesBySpecialization(GetCoachesBySpecializationRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
        List<Coach> coaches = clientRepository.findPersonalCoachesBySpecialization(client.getId(), request.specialization());
        return coachesToUserGetCoachesResponse(coaches);
    }

    @Override
    public List<GetCoachesResponse> getAllPersonalCoachesByName(GetCoachesByNameRequest request) {
        Client client = LaboFitnessUtil.getAuthentication(Client.class);
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

        Client client = LaboFitnessUtil.getAuthentication(Client.class);

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
    public TrainingSuscribResponse subscribeToOneTrainingSession(TrainingSuscribRequest request) {
        String message;
        Client client = LaboFitnessUtil.getAuthentication(Client.class);

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
                    " du " + LaboFitnessUtil.DateToStringFormatDayMonthValueYear(training.getStart_date()) + " with success !";
            }
        else {
            throw new InscriptionCloseException("Training session inscription closed");
        }

        return new TrainingSuscribResponse(message);
    }


    // endregion

}