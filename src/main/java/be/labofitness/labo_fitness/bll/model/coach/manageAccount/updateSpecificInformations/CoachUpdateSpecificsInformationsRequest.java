package be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations;

/**
 * Represents the request model for changing the specific information of a coach's account.
 * <p>
 * This record encapsulates the idRemote information and the pricePerHour.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * boolean  isRemote = true;
 * int pricePerHour = 100;
 * CoachUpdateSpecificsInformationsRequest request = new CoachUpdateSpecificsInformationsRequest(oldPassword, newPassword);
 * }</pre>
 *
 * @param isRemote the remote option of the coach's account.
 * @param pricePerHour The price per hour of the coach.
 */
public record CoachUpdateSpecificsInformationsRequest(

        boolean isRemote,
        int pricePerHour
) {
}
