package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.service.service.RoleService;
import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Report;
import be.labofitness.labo_fitness.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

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

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Role} by its ID.
     *
     * @param id the ID of the {@link Role} to retrieve
     * @return the {@link Role} with the given ID
     */
    @Override
    public Role getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Role}.
     *
     * @return a list of all {@link Role}
     */
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    /**
     * Creates a new {@link Role}.
     *
     * @param entity the {@link Role} to create
     * @return the created {@link Role}
     */
    @Override
    public Role create(Role entity) {
        return null;
    }

    /**
     * Updates an existing {@link Role}.
     *
     * @param entity the {@link Role} to update
     * @return the updated {@link Role}
     */
    @Override
    public Role update(Role entity) {
        return null;
    }

    /**
     * Deletes an {@link Role} by its ID.
     *
     * @param id the ID of the {@link Role} to delete
     * @return the deleted {@link Role}, or null if not found
     */
    @Override
    public Role delete(Long id) {
        return null;
    }

    // endregion

}
