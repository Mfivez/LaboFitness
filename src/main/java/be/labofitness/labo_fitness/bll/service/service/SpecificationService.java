package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.dal.util.HasFindByMethod;
import be.labofitness.labo_fitness.dal.util.HasGetIdMethod;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.function.Function;

public interface SpecificationService {

    <T, R> Specification<T> specificationHasSomething(Specification<T> spec, R var, Function<R, Specification<T>> specBuilder);

    <T, R> Specification<T> specificationHasCollectionOfSomething(Specification<T> spec, Collection<R> collection, Function<R, Specification<T>> specBuilder);

    <S extends HasGetIdMethod, T extends HasFindByMethod<S>> Long getIdByMail(String mail, T repository);

    <T> Specification<T> specificationHasAnyBoolean(Specification<T> spec, String status, Function<Boolean, Specification<T>> specBuilder);

}
