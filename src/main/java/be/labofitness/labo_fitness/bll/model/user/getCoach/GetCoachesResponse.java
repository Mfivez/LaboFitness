package be.labofitness.labo_fitness.bll.model.user.getCoach;
import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import lombok.Data;

import java.util.Set;

/**
 * Represents the response model for retrieving coaches.
 * <p>
 * This class encapsulates various details such as whether the coach offers remote services,
 * hourly price, email, work schedule, specialization, and location places.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Coach coach = coachService.getOne(coachId)
 * GetCoachesResponse response = GetCoachesResponse.fromEntity(coach);
 * }</pre>
 */
@Data
public class GetCoachesResponse {
    private boolean is_remote;
    private int price_hour;
    private String email;
    private String workSchedule;
    private String specialization;
    Set<LocationPlace> locationPlace;

    public GetCoachesResponse(boolean isRemote, int priceHour, String email, String workSchedule, Set<LocationPlace> locationPlace, String specialization) {
        this.is_remote = isRemote;
        this.price_hour = priceHour;
        this.email = email;
        this.workSchedule = workSchedule;
        this.locationPlace = locationPlace;
        this.specialization = specialization;
    }

    /**
     * Constructs a {@code GetCoachesResponse} object from a {@link Coach} entity.
     *
     * @param coach The coach entity.
     * @return A new {@code GetCoachesResponse} object.
     */
    public static GetCoachesResponse fromEntity(Coach coach) {
        return new GetCoachesResponse(
                coach.isRemote(),
                coach.getPriceHour(),
                coach.getEmail(),
                coach.getWorkSchedule(),
                coach.getLocationPlace(),
                coach.getSpecialization()
        );
    }

}
