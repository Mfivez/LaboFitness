package be.labofitness.labo_fitness.bll.models.response.user.getPhysiotherapist;

import be.labofitness.labo_fitness.domain.entity.Coach;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import lombok.Data;

import java.util.Set;

@Data
public class UserGetPhysioResponse {
    private String email;
    private String workSchedule;
    private String specialization;
    Set<LocationPlace> locationPlace;

    public UserGetPhysioResponse(String email, String workSchedule, Set<LocationPlace> locationPlace, String specialization) {
        this.email = email;
        this.workSchedule = workSchedule;
        this.locationPlace = locationPlace;
        this.specialization = specialization;
    }

    public static UserGetPhysioResponse fromEntity(Physiotherapist physio) {
        return new UserGetPhysioResponse(
                physio.getEmail(),
                physio.getWorkSchedule(),
                physio.getLocationPlace(),
                physio.getSpecialization()
        );
    }

}
