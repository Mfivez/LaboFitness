package be.labofitness.labo_fitness.il.utils;

import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import be.labofitness.labo_fitness.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;
import java.util.stream.Collectors;


@Configuration
@RequiredArgsConstructor
public class LaboFitnessUtil {

    public static LocalDateTime createNewDate(int year, Month month, int day) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,iniTime, iniTime,iniTime);
    }

    //v12.2
    public static Set<Role> setRole(Set<String> roles, RoleRepository roleRepository) {
        return roles.stream()
                .map(role -> switch (role) {
                    case "CLIENT" -> roleRepository.findById(2L).orElseThrow(RuntimeException::new);
                    case "PHYSIOTHERAPIST" -> roleRepository.findById(3L).orElseThrow(RuntimeException::new);
                    case "COACH" -> roleRepository.findById(4L).orElseThrow(RuntimeException::new);
                    case "MODERATOR" -> roleRepository.findById(5L).orElseThrow(RuntimeException::new);
                    case "ADMIN" -> roleRepository.findById(6L).orElseThrow(RuntimeException::new);
                    default -> throw new IllegalArgumentException("Invalid role: " + role);
                }).collect(Collectors.toSet());
        //BUG GITHUB

//        v6.0
//        return switch (role) {
//            case "CLIENT" -> roleRepository.findById(2L).orElseThrow(RuntimeException::new);
//            case "PHYSIOTHERAPIST" -> roleRepository.findById(3L).orElseThrow(RuntimeException::new);
//            case "COACH" -> roleRepository.findById(4L).orElseThrow(RuntimeException::new);
//            case "MODERATOR" -> roleRepository.findById(5L).orElseThrow(RuntimeException::new);
//            case "ADMIN" -> roleRepository.findById(6L).orElseThrow(RuntimeException::new);
//            default -> throw new IllegalArgumentException("Invalid role: " + role);
//        };
//   }
}}
