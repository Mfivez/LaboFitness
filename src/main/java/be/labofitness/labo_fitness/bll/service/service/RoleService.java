package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Role;

import java.util.List;
import java.util.Set;


public interface RoleService  extends CrudService<Role, Long> {

    Set<Role> setRole(Set<String> roles, RoleRepository roleRepository);

}
