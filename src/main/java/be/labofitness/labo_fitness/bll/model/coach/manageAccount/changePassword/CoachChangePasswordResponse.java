package be.labofitness.labo_fitness.bll.model.coach.manageAccount.changePassword;
import be.labofitness.labo_fitness.domain.entity.Coach;

/**
 * Represents the response model for changing the password of a coach's account.
 * <p>
 * This record encapsulates the ID of the coach and a message indicating the success of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Coach coach = coachService.getCoachById(id);
 * String successMessage = "Password change ";
 * CoachChangePasswordResponse response = CoachChangePasswordResponse.fromEntity(coach, successMessage);
 * }</pre>
 *
 * @param id      The ID of the coach.
 * @param message A message indicating the success of the password change operation.
 */
public record CoachChangePasswordResponse (
        Long id,
        String message
) {

    /**
     * Converts a {@link Coach} entity object to a {@link CoachChangePasswordResponse} object.
     *
     * @param coach   The {@link Coach} entity object.
     * @param message A message indicating the success of the password change operation.
     * @return A {@link CoachChangePasswordResponse} object created from the Coach entity.
     */
    public static CoachChangePasswordResponse fromEntity(Coach coach, String message){
        return new CoachChangePasswordResponse(
                coach.getId(),
                message + "successfully done"
        );
    }
}
