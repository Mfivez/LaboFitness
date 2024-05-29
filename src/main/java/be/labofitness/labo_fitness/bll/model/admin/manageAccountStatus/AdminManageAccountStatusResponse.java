package be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus;

import be.labofitness.labo_fitness.domain.entity.User;

public record AdminManageAccountStatusResponse(

        Long id,
        String message
) {

    public static AdminManageAccountStatusResponse fromEntity(User user, String message){
        return new AdminManageAccountStatusResponse(
                user.getId(),
                message + "successfully done"
        );
    }

}
