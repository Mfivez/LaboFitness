package be.labofitness.labo_fitness.bll.service.impl.security;
import be.labofitness.labo_fitness.bll.exception.Authentication.AuthenticationException;
import be.labofitness.labo_fitness.bll.service.service.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link SecurityService} interface.
 * Provides methods for handling security-related operations.
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {


    /**
     * Retrieves the current authentication object and casts it to the specified type.
     *
     * @param type the type to which the authentication object should be cast
     * @param <T> the generic type representing the desired class
     * @return the authenticated principal object of the specified type
     * @throws AuthenticationException if the authentication object is null
     * @throws AuthenticationException if casting the authentication object to the specified type fails
     */
    @Override
    public <T> T getAuthentication(Class<T> type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AuthenticationException("Authentication is null", 401);}

        if (type.isInstance(authentication.getPrincipal())) {
            return type.cast(authentication.getPrincipal());
        }
        else throw new AuthenticationException(
                "Authentication casting type failed: Authentication is not of type " + type.getName(), 401);
    }
}
