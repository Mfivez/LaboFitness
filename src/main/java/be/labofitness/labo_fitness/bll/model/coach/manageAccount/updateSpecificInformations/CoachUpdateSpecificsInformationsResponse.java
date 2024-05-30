package be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations;

import be.labofitness.labo_fitness.domain.entity.Coach;

public record CoachUpdateSpecificsInformationsResponse(

        Long id,
        String message
) {
    public static CoachUpdateSpecificsInformationsResponse fromEntity(Coach coach, String message){
        return new CoachUpdateSpecificsInformationsResponse(
                coach.getId(),
                "successfully done"
        );
    }
}
