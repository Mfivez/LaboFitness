package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;

import java.util.Set;

/**
 * Service interface for managing {@link Role}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface RoleService  extends CrudService<Role, Long> {

    /**
     * Sets {@link Role} for a {@link User} based on the provided set of {@link Role} names.
     *
     * @param roles           the set of {@link Role} names to assign to the {@link User}
     * @return the set of {@link Role} assigned to the {@link User}
     */
    Set<Role> setRole(Set<String> roles);

}
