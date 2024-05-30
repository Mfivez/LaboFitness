package be.labofitness.labo_fitness.bll.model.client.manageAccount.changePassword;
import be.labofitness.labo_fitness.domain.entity.Client;

/**
 * Represents the response model for changing the password of a client's account.
 * <p>
 * This record encapsulates the ID of the client and a message indicating the success of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Client client = clientService.getClient(id);
 * String successMessage = "Password ";
 * ClientChangePasswordResponse response = ClientChangePasswordResponse.fromEntity(client, successMessage);
 * }</pre>
 *
 * @param id      The ID of the client.
 * @param message A message indicating the success of the operation.
 */
public record ClientChangePasswordResponse (
        Long id,
        String message
) {

    /**
     * Converts a {@link Client} entity object to a {@link ClientChangePasswordResponse} object.
     *
     * @param client  The {@link Client} entity object.
     * @param message The message indicating the success of the operation.
     * @return A {@link ClientChangePasswordResponse} object created from the Client entity.
     */
    public static ClientChangePasswordResponse fromEntity(Client client, String message){
        return new ClientChangePasswordResponse(
                client.getId(),
                message + "successfully updated"
        );
    }
}
