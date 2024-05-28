package be.labofitness.labo_fitness.bll.model.response.coach.manageAccount;

import be.labofitness.labo_fitness.domain.entity.Coach;

public record CoachManageAccountResponse(

        String name,
        Long id,
        String message
        //todo send email to client with "account modified"
) {

    public static CoachManageAccountResponse fromEntity(Coach coach,String message){
        return new CoachManageAccountResponse(
                coach.getName(),
                coach.getId(),
                message + " successfully done"
        );

    }
}
