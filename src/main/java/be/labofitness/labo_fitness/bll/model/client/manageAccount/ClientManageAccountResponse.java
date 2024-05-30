package be.labofitness.labo_fitness.bll.model.client.manageAccount;
import be.labofitness.labo_fitness.domain.entity.Client;

/**
 * Represents the response model for managing a client's account.
 * <p>
 * This record encapsulates the name of the client, the client's ID, and a message indicating the success of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Client client = clientService.getOne(id);
 * String successMessage = "Account modification ";
 * ClientManageAccountResponse response = ClientManageAccountResponse.fromEntity(client, successMessage);
 * }</pre>
 *
 * @param name    The name of the client.
 * @param id      The ID of the client.
 * @param message A message indicating the success of the operation.
 */
public record ClientManageAccountResponse (
        String name,
        Long id,
        String message
        //todo send email to client with "account modified"
) {

    /**
     * Converts a {@link Client} entity object to a {@link ClientManageAccountResponse} object.
     *
     * @param client  The {@link Client} entity object.
     * @param message The message indicating the success of the operation.
     * @return A {@link ClientManageAccountResponse} object created from the Client entity.
     */
    public static ClientManageAccountResponse fromEntity(Client client,String message) {
        return new ClientManageAccountResponse(
                client.getName(),
                client.getId(),
                message + " successfully done"
        );
    }

}




