package be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

/**
 * Represents the response model for managing the account of a physiotherapist.
 * <p>
 * This record encapsulates the name, ID, and a message indicating the success of the operation.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Physiotherapist physiotherapist = new Physiotherapist("John", 1L);
 * String message = "Update ";
 * PhysiotherapistManageAccountResponse response = PhysiotherapistManageAccountResponse.fromEntity(physiotherapist, message);
 * }</pre>
 *
 * @param name    The name of the physiotherapist.
 * @param id      The ID of the physiotherapist.
 * @param message The message indicating the success of the operation.
 */
public record PhysiotherapistManageAccountResponse(
        String name,
        Long id,
        String message
) {
    public static PhysiotherapistManageAccountResponse fromEntity(Physiotherapist physiotherapist,String message){
        return new PhysiotherapistManageAccountResponse(
                physiotherapist.getName(),
                physiotherapist.getId(),
                message + "successfully done"
        );
    }
}
