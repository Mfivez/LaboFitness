package be.labofitness.labo_fitness.bll.models.response.user.getCoach;

import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import lombok.Data;

import java.util.Set;

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
