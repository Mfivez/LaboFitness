package be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.changePassWord;

import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

/**
 * Represents the response model for changing the password of a physiotherapist.
 * <p>
 * This record encapsulates the ID of the physiotherapist and a message indicating the success of the operation.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Physiotherapist physiotherapist = physioService.getById(id);
 * String successMessage = "Password change ";
 * PhysiotherapistChangePasswordResponse response = PhysiotherapistChangePasswordResponse.fromEntity(physiotherapist, successMessage);
 * }</pre>
 *
 * @param id      The ID of the physiotherapist.
 * @param message A message indicating the success of the operation.
 */
public record PhysiotherapistChangePasswordResponse(
        Long id,
        String message
) {

    /**
     * Converts a {@link Physiotherapist} entity object to a {@link PhysiotherapistChangePasswordResponse} object.
     *
     * @param physiotherapist The {@link Physiotherapist} entity object.
     * @param message         The message indicating the success of the operation.
     * @return A {@link PhysiotherapistChangePasswordResponse} object created from the Physiotherapist entity.
     */
    public static PhysiotherapistChangePasswordResponse fromEntity(Physiotherapist physiotherapist, String message) {
        return new PhysiotherapistChangePasswordResponse(
                physiotherapist.getId(),
                message + " successfully done"
        );
    }
}

