package be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist;
import be.labofitness.labo_fitness.domain.entity.LocationPlace;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import lombok.Data;

import java.util.Set;

/**
 * Represents the response model for retrieving physiotherapists.
 * <p>
 * This class encapsulates various details such as email, work schedule, specialization, and location places of physiotherapists.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Physiotherapist physio = new Physiotherapist();
 * GetPhysioResponse response = GetPhysioResponse.fromEntity(physio);
 * }</pre>
 */
@Data
public class GetPhysioResponse {
    private String email;
    private String workSchedule;
    private String specialization;
    Set<LocationPlace> locationPlace;

    public GetPhysioResponse(String email, String workSchedule, Set<LocationPlace> locationPlace, String specialization) {
        this.email = email;
        this.workSchedule = workSchedule;
        this.locationPlace = locationPlace;
        this.specialization = specialization;
    }

    /**
     * Constructs a {@code GetPhysioResponse} object from a {@link Physiotherapist} entity.
     *
     * @param physio The physiotherapist entity.
     * @return A new {@code GetPhysioResponse} object.
     */
    public static GetPhysioResponse fromEntity(Physiotherapist physio) {
        return new GetPhysioResponse(
                physio.getEmail(),
                physio.getWorkSchedule(),
                physio.getLocationPlace(),
                physio.getSpecialization()
        );
    }

}
