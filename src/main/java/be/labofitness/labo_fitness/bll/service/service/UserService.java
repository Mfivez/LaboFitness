package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.model.request.user.getCoach.GetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getCoach.GetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getCoach.GetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.request.UserLoginRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getPhysiotherapist.GetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getPhysiotherapist.GetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getTrainingSession.GetTrainingSessionByRecommendedLvlRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getTrainingSession.GetTrainingSessionsByCoachNameRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getTrainingSession.GetTrainingSessionsByDurationRequest;
import be.labofitness.labo_fitness.bll.model.request.user.getTrainingSession.GetTrainingSessionsByNameRequest;
import be.labofitness.labo_fitness.bll.model.request.user.makeReport.MakeReportRequest;
import be.labofitness.labo_fitness.bll.model.response.user.getReport.GetReportResponse;
import be.labofitness.labo_fitness.bll.model.response.user.getTrainingSession.GetTrainingSessionResponse;
import be.labofitness.labo_fitness.bll.model.response.user.getCoach.GetCoachesResponse;
import be.labofitness.labo_fitness.bll.model.response.UserLoginResponse;
import be.labofitness.labo_fitness.bll.model.response.user.getPhysiotherapist.GetPhysioResponse;
import be.labofitness.labo_fitness.bll.model.response.user.makeReport.ReportResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

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

    List<GetTrainingSessionResponse> findAllTrainingSession();

    List<GetTrainingSessionResponse> findTrainingSessionByRecommendedLvl(GetTrainingSessionByRecommendedLvlRequest request);

    List<GetTrainingSessionResponse> findTrainingSessionByDuration(GetTrainingSessionsByDurationRequest request);

    List<GetTrainingSessionResponse> findTrainingSessionByName(GetTrainingSessionsByNameRequest request);

    List<GetTrainingSessionResponse> findTrainingSessionByCoachName(GetTrainingSessionsByCoachNameRequest request);

    // endregion

    // region REPORT

    //region MAKE REPORT

    ReportResponse makeReport(MakeReportRequest request);

    //endregion

    // region GET REPORT

    Set<GetReportResponse> getValidReport();

    // endregion

    // endregion
}
