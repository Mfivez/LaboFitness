package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.exception.Unauthorize.UnauthorizedException;
import be.labofitness.labo_fitness.bll.service.service.SpecificationService;
import be.labofitness.labo_fitness.dal.util.HasFindByMethod;
import be.labofitness.labo_fitness.dal.util.HasGetIdMethod;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

/**
 * Implementation of the {@link SpecificationService} interface.
 *
 * <p>This service provides utility methods for constructing {@code JPA specifications}
 * based on various conditions and {@code criteria}. It is designed to facilitate the
 * creation of dynamic queries in a type-safe manner.</p>
 *
 * <p>It includes methods to:
 * <ul>
 *   <li>Build specifications based on the presence of specific values or collections of values.</li>
 *   <li>Retrieve entity IDs based on email addresses.</li>
 *   <li>Construct specifications based on boolean conditions.</li>
 * </ul>
 * </p>
 *
 * <p>Exceptions such as {@link DoesntExistException} and {@link UnauthorizedException}
 * are used to handle cases where specific conditions are not met or values are invalid.</p>
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    /**
     * Constructs a {@link Specification} by checking if a variable has a specific value.
     *
     * @param <T>          the type of the entity to which the specification applies
     * @param <R>          the type of the variable being checked
     * @param spec         the base specification to be extended
     * @param var          the variable to be checked
     * @param specBuilder  a function that builds a specification based on the variable
     * @return the combined specification
     */
    public <T, R> Specification<T> specificationHasSomething(Specification<T> spec, R var, Function<R, Specification<T>> specBuilder) {
        return (  var != null ?
                (  var instanceof String ?
                        (  !((String) var).isEmpty() ?
                                spec.and(specBuilder.apply(var)) : spec  ) : spec.and(specBuilder.apply(var))  ) : spec  );
    }

    /**
     * Constructs a {@link Specification} by checking if elements in a collection have a specific value.
     *
     * @param <T>          the type of the entity to which the specification applies
     * @param <R>          the type of the elements in the collection
     * @param spec         the base specification to be extended
     * @param collection   the collection of elements to be checked
     * @param specBuilder  a function that builds a specification based on each element
     * @return the combined specification
     */
    public <T, R> Specification<T> specificationHasCollectionOfSomething(Specification<T> spec, Collection<R> collection, Function<R, Specification<T>> specBuilder) {
        if (collection != null) {
            for (R element : collection) {
                spec = (element != null ?
                        (element instanceof String ?
                                (!((String) element).isEmpty() ?
                                        spec.and(specBuilder.apply(element)) : spec) : spec.and(specBuilder.apply(element))) : spec);
            }  return spec;
        } return spec;
    }

    /**
     * Retrieves the T ID associated with the specified email.
     *
     * <p>This method queries the {@code T Repository} to find a T by their email address.
     * If no T is found, a {@link DoesntExistException} is thrown.</p>
     *
     * @param <S>         the type of the entity that has an ID method
     * @param <T>         the type of the repository used to find the entity by email
     * @param mail        the email address of the T
     * @param repository  the repository used to find the T
     * @return the ID of the T associated with the specified email
     * @throws DoesntExistException if no T with the specified email is found
     */
    public <S extends HasGetIdMethod, T extends HasFindByMethod<S>> Long getIdByMail(String mail, T repository) {
        return mail != null ? repository.findByEmail(mail).orElseThrow(
                () -> new DoesntExistException("Email doesn't exist: " + mail)).getId() : null ;
    }

    /**
     * Constructs a {@link Specification} by checking if a boolean value is true, false, or any.
     *
     * @param <T>          the type of the entity to which the specification applies
     * @param spec         the base specification to be extended
     * @param status       the string representation of the boolean value
     * @param specBuilder  a function that builds a specification based on the boolean value
     * @return the combined specification
     * @throws UnauthorizedException if the value of 'status' is not 'true', 'false', or 'any'
     */
    public <T> Specification<T> specificationHasAnyBoolean(Specification<T> spec, String status, Function<Boolean, Specification<T>> specBuilder) {
        return status != null ? switch (status.toLowerCase()) {
            case "true" -> spec = spec.and(specBuilder.apply(true));
            case "false" -> spec = spec.and(specBuilder.apply(false));
            case "any" -> spec = spec.and(Specification.where(specBuilder.apply(false)).or(specBuilder.apply(true)));
            default -> throw new UnauthorizedException("The value of 'status' must be 'true' 'false' or 'any.");
        } : spec ;
    }

}
