package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.service.service.SpecificationService;
import be.labofitness.labo_fitness.dal.util.HasFindByMethod;
import be.labofitness.labo_fitness.dal.util.HasGetIdMethod;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

@Service
public class SpecificationServiceImpl implements SpecificationService {


    public <T, R> Specification<T> specificationHasSomething(Specification<T> spec, R var, Function<R, Specification<T>> specBuilder) {
        return (  var != null ?
                (  var instanceof String ?
                        (  !((String) var).isEmpty() ?
                                spec.and(specBuilder.apply(var)) : spec  ) : spec.and(specBuilder.apply(var))  ) : spec  );
    }

    public <T, R> Specification<T> specificationHasCollectionOfSomething(Specification<T> spec, Collection<R> collection, Function<R, Specification<T>> specBuilder) {
        for (R element : collection) {
            spec = (element != null ?
                    (element instanceof String ?
                            (!((String) element).isEmpty() ?
                                    spec.and(specBuilder.apply(element)) : spec) : spec.and(specBuilder.apply(element))) : spec);
        }
        return spec;
    }

    /**
     * Retrieves the T ID associated with the specified email.
     *
     * <p>This method queries the {@code T Repository} to find a T by their email address.
     * If no T is found, a {@code DoesntExistException} is thrown.</p>
     *
     * @param mail the email address of the T
     * @return the ID of the T associated with the specified email
     * @throws DoesntExistException if no T with the specified email is found
     */
    public <S extends HasGetIdMethod, T extends HasFindByMethod<S>> Long getIdByMail(String mail, T repository) {
        return mail != null ? repository.findByEmail(mail).orElseThrow(
                () -> new DoesntExistException("Email doesn't exist: " + mail)).getId() : null ;
    }

}
