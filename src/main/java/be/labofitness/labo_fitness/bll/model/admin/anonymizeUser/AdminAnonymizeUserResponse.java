package be.labofitness.labo_fitness.bll.model.admin.anonymizeUser;

import be.labofitness.labo_fitness.domain.entity.User;

public record AdminAnonymizeUserResponse(
        Long id,
        String message
) {
    public static AdminAnonymizeUserResponse fromEntity(User user, String message){
        return new AdminAnonymizeUserResponse(
                user.getId(),
                message + "successfully done"
        );
    }
}
