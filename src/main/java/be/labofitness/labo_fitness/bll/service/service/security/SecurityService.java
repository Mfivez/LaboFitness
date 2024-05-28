package be.labofitness.labo_fitness.bll.service.service.security;

public interface SecurityService {

    /**
     * Retrieves the authentication object of the specified type.
     *
     * @param type the class representing the type of authentication object to retrieve
     * @param <T>  the type of authentication object
     * @return the authentication object of the specified type
     */
    <T> T getAuthentication(Class<T> type);

}
