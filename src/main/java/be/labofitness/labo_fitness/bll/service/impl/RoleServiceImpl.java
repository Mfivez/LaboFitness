package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> setRole(Set<String> roles, RoleRepository roleRepository) {
        //TODO REFACTO -> JUST TRY CATCH WITH FILTER AND DELETE SWITCH
        return roles.stream()
                .map(role -> switch (role) {
                    case "USER" -> roleRepository.findByName("USER").orElseThrow(RuntimeException::new);
                    case "CLIENT" -> roleRepository.findByName("CLIENT").orElseThrow(RuntimeException::new);
                    case "PHYSIOTHERAPIST" -> roleRepository.findByName("PHYSIOTHERAPIST").orElseThrow(RuntimeException::new);
                    case "COACH" -> roleRepository.findByName("COACH").orElseThrow(RuntimeException::new);
                    case "MODERATOR" -> roleRepository.findByName("MODERATOR").orElseThrow(RuntimeException::new);
                    case "ADMIN" -> roleRepository.findByName("ADMIN").orElseThrow(RuntimeException::new);
                    default -> throw new IllegalArgumentException("Invalid role: " + role);
                }).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Role getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return List.of();
    }

    @Override
    public Role create(Role entity) {
        return null;
    }

    @Override
    public Role update(Role entity) {
        return null;
    }

    @Override
    public Role delete(Long aLong) {
        return null;
    }

}
