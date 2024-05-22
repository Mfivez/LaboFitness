package be.labofitness.labo_fitness.bll.models.request.client.CompetitionRegister;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public record CompetitionRegisterRequest(

        String competitionName,
        LocalDateTime startDate

) {
}
