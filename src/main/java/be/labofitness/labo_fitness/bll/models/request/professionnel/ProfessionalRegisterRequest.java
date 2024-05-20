package be.labofitness.labo_fitness.bll.models.request.professionnel;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Role;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;


import java.time.Month;
//BUG GITHUB
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
