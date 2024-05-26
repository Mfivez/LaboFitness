package be.labofitness.labo_fitness.bll.service.service.security;

public interface SecurityService {

    <T> T getAuthentication(Class<T> type);

}
