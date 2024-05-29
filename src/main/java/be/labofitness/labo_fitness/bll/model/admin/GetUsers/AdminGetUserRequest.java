package be.labofitness.labo_fitness.bll.model.admin.GetUsers;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;

import java.util.List;


/**
 * Represents the request model for retrieving {@link User} information in the admin module.
 */
public record AdminGetUserRequest(

        List<String> roles,

        String userEmail,

        String name,

        Long id,

        Boolean isActive,

        Boolean isRemote,

        String inamiNumber


) {
}
