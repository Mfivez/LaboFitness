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

    public static LocalDateTime createNewDate(int year, Month month, int day, int hour) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,hour, iniTime,iniTime);
    }

    public static LocalDateTime createNewDate(int year, Month month, int day, int hour, int minute) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,hour, minute,iniTime);
    }

    public static String CompetitionNameIdBuilder(String name, LocalDateTime dateTime) {
        return name + "Of" + dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear();
    }

    //v12.2
    public static Set<Role> setRole(Set<String> roles, RoleRepository roleRepository) {
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
