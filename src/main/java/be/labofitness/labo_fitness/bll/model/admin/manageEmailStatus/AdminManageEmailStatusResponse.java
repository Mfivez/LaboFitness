package be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus;

import be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus.AdminManageAccountStatusResponse;
import be.labofitness.labo_fitness.domain.entity.User;

public record AdminManageEmailStatusResponse(

        Long id,
        String message

) {
    public static AdminManageEmailStatusResponse fromEntity(User user, String message){
        return new AdminManageEmailStatusResponse(
                user.getId(),
                message + "successfully done"
        );
    }
}
