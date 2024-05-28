package be.labofitness.labo_fitness.bll.model.register;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;


import java.time.Month;

public record ProfessionalRegisterRequest(
        String name,
        String lastName,
        int year,
        Month month,
        int day,
        String email,
        String password,
        AccreditationType accreditation,
        String accreditationDescription,
        String role


) {
}
