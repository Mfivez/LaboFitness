package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.UserLoginRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.models.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.UserLoginResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.models.response.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends CrudService<User, Long> , UserDetailsService {

    UserLoginResponse login(UserLoginRequest loginRequest);

    //region COACH
    List<GetCoachesResponse> getAllCoaches();

    List<GetCoachesResponse> getAllCoachesByIsRemote(GetCoachesByRemoteRequest request);

    List<GetCoachesResponse> getAllCoachesBySpecialization(GetCoachesBySpecializationRequest request);

    List<GetCoachesResponse> getAllCoachesByName(GetCoachesByNameRequest request);

    // endregion

    //region PHYSIOTHERAPIST
    List<GetPhysioResponse> getAllPhysio();

    List<GetPhysioResponse> getPhysioBySpecialization(GetPhysioBySpecializationRequest request);

    List<GetPhysioResponse> getPhysioByName(GetPhysioByNameRequest request);
    //endregion

    // region TRAINING SESSIONS

    public List<GetTrainingSessionResponse> findAllTrainingSession();

    public List<GetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request);

    public List<GetTrainingSessionResponse> findTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request);

    public List<GetTrainingSessionResponse> findTrainingSessionByName(GetTrainingSessionsByNameRequest request);

    public List<GetTrainingSessionResponse> findTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request);

    // endregion

    //region MAKE REPORT

    ReportResponse makeReport(Authentication authentication, MakeReportRequest request);

    //endregion

}
