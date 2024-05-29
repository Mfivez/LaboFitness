package be.labofitness.labo_fitness.bll.specification;

import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class AdminGetUserSpecification {

    public static Specification<User> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<User> hasUserId(Long userId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), userId);
    }

    public static Specification<User> hasInamiNumber(String inamiNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("inamiNumber")), "%" + inamiNumber.toLowerCase() + "%");

    }

    public static Specification<User> isRemote(boolean remote) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isRemote"), remote);
    }

    public static Specification<User> isActive(boolean active) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isActive"), active);
    }

    public static Specification<User> hasRole(String role){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.join("roles").get("name")), "%" + role.toLowerCase() + "%");
    }









}
