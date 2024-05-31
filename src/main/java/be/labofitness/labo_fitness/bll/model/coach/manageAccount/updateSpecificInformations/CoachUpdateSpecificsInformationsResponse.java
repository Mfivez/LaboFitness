package be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations;

import be.labofitness.labo_fitness.domain.entity.Coach;

/**
 * Represents the request model for changing the specific information of a coach's account.
 * <p>
 * This record encapsulates the idRemote information and the pricePerHour.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * coochId = 1
 * CoachUpdateSpecific informations successfully done
 * CoachUpdateSpecificsInformationsResponse response = new CoachUpdateSpecificsInformationsResponse(coach, message);
 * }</pre>
 *
 * @param id the id of the modified coach.
 */
public record CoachUpdateSpecificsInformationsResponse(

        Long id,
        String message
) {
    public static CoachUpdateSpecificsInformationsResponse fromEntity(Coach coach, String message){
        return new CoachUpdateSpecificsInformationsResponse(
                coach.getId(),
                message + "successfully done"
        );
    }
}
