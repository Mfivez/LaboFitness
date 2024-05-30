package be.labofitness.labo_fitness.bll.model.client.CompetitionRegister;
import be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.StringValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Represents the request model for registering for a competition by a client.
 * <p>
 * This record encapsulates the name and start date of the competition.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String competitionName = "Example Competition";
 * LocalDateTime startDate = LocalDateTime.now();
 * CompetitionRegisterRequest request = new CompetitionRegisterRequest(competitionName, startDate);
 * }</pre>
 *
 * @param competitionName The name of the competition.
 * @param startDate       The start date of the competition.
 */
public record CompetitionRegisterRequest(

        @NotNull(message = "error.client.competitionName.null")
        @StringValid(entity = "Competition", field = "name", min = 5)
        String competitionName,

        @NotNull(message = "error.client.startDate.null")
        @NotBlank(message = "error.client.startDate.blank")
        LocalDateTime startDate

) {
}
