package be.labofitness.labo_fitness.bll.model.coach.manageAccount;
import be.labofitness.labo_fitness.domain.entity.Coach;

/**
 * Represents the response model for managing the account of a coach.
 * <p>
 * This record encapsulates the name, ID, and a message indicating the success of the operation.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * Coach coach = coachService.getCoachById(coachId);
 * String successMessage = "Account modification ";
 * CoachManageAccountResponse response = CoachManageAccountResponse.fromEntity(coach, successMessage);
 * }</pre>
 *
 * @param name    The name of the coach.
 * @param id      The ID of the coach.
 * @param message A message indicating the success of the operation.
 */
public record CoachManageAccountResponse(

        String name,
        Long id,
        String message
        //todo send email to client with "account modified"
) {

    /**
     * Converts a {@link Coach} entity object to a {@link CoachManageAccountResponse} object.
     *
     * @param coach   The {@link Coach} entity object.
     * @param message The message indicating the success of the operation.
     * @return A {@link CoachManageAccountResponse} object created from the Coach entity.
     */
    public static CoachManageAccountResponse fromEntity(Coach coach,String message){
        return new CoachManageAccountResponse(
                coach.getName(),
                coach.getId(),
                message + " successfully done"
        );

    }
}
