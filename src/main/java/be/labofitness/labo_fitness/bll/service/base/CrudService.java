package be.labofitness.labo_fitness.bll.service.base;

import java.util.List;

public interface CrudService<T, ID> {

    T getOne(ID id);

    List<T> getAll();

    T create(T entity);

    T update(T entity);

    T delete(ID id);

}