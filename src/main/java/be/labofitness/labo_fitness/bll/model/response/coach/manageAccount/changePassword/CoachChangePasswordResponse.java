package be.labofitness.labo_fitness.bll.model.response.coach.manageAccount.changePassword;

import be.labofitness.labo_fitness.domain.entity.Coach;

public record CoachChangePasswordResponse (

        Long id,
        String message
) {
    public static CoachChangePasswordResponse fromEntity(Coach coach, String message){
        return new CoachChangePasswordResponse(
                coach.getId(),
                message + "successfully done"
        );
    }
}
