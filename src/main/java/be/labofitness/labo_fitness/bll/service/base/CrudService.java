package be.labofitness.labo_fitness.bll.service.base;
import java.util.List;

/**
 * Generic service interface for {@code basic CRUD operations}.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity's identifier
 */
public interface CrudService<T, ID> {

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return the entity with the given ID
     */
    T getOne(ID id);

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    List<T> getAll();

    /**
     * Creates a new entity.
     *
     * @param entity the entity to create
     * @return the created entity
     */
    T create(T entity);

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     * @return the deleted entity
     */
    T delete(ID id);

}