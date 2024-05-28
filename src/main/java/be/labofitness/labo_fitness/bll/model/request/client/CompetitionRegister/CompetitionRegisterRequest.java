package be.labofitness.labo_fitness.bll.model.request.client.CompetitionRegister;

import java.time.LocalDateTime;

public record CompetitionRegisterRequest(

        String competitionName,
        LocalDateTime startDate

) {
}
