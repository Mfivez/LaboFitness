package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesByRemoteRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getCoach.UserGetCoachesBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.request.UserLoginRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.UserGetPhysioByNameRequest;
import be.labofitness.labo_fitness.bll.models.request.user.getPhysiotherapist.UserGetPhysioBySpecializationRequest;
import be.labofitness.labo_fitness.bll.models.response.user.getCoach.UserGetCoachesResponse;
import be.labofitness.labo_fitness.bll.models.response.UserLoginResponse;
import be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist.UserGetPhysioResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends CrudService<User, Long> , UserDetailsService {


    UserLoginResponse login(UserLoginRequest loginRequest);


    //region COACH
    List<UserGetCoachesResponse> getAllCoaches();

    List<UserGetCoachesResponse> getAllCoachesByIsRemote(UserGetCoachesByRemoteRequest request);

    List<UserGetCoachesResponse> getAllCoachesBySpecialization(UserGetCoachesBySpecializationRequest request);

    List<UserGetCoachesResponse> getAllCoachesByName(UserGetCoachesByNameRequest request);

    // endregion

    //region PHYSIOTHERAPIST
    List<UserGetPhysioResponse> getAllPhysio();

    List<UserGetPhysioResponse> getPhysioBySpecialization(UserGetPhysioBySpecializationRequest request);

    List<UserGetPhysioResponse> getPhysioByName(UserGetPhysioByNameRequest request);
    //endregion

}
