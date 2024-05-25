package be.labofitness.labo_fitness.bll.models.response.client.manageAccount;

import be.labofitness.labo_fitness.domain.entity.Client;

public record ClientManageAccountResponse (

        String name,
        Long id,
        String message

        //todo send email to client with "account modified"
) {

    public static ClientManageAccountResponse fromEntity(Client client,String message) {
        return new ClientManageAccountResponse(
                client.getName(),
                client.getId(),
                message + " successfully done"
        );
    }

}




