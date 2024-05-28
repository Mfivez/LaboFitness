package be.labofitness.labo_fitness.bll.model.login;

import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.entity.User;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserLoginResponse {
    private Long id;
    private String login;
    private Set<String> roles;
    private String token;

    public UserLoginResponse(Long id, String login, Set<String> roles, String token) {
        this.id = id;
        this.login = login;
        this.roles = roles;
        this.token = token;
    }

    public static UserLoginResponse fromEntity(User user, String token) {
        return new UserLoginResponse(user.getId(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()), token);
    }
}
