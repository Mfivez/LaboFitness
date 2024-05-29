package be.labofitness.labo_fitness.bll.model.client.manageAccount.changePassword;

import be.labofitness.labo_fitness.domain.entity.Client;

public record ClientChangePasswordResponse (
        Long id,
        String message
) {
    public static ClientChangePasswordResponse fromEntity(Client client, String message){
        return new ClientChangePasswordResponse(
                client.getId(),
                message + "successfully updated"
        );
    }
}
