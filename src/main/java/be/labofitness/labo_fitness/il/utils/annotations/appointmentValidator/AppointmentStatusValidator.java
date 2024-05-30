package be.labofitness.labo_fitness.il.utils.annotations.appointmentValidator;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
public class AppointmentStatusValidator implements ConstraintValidator<AppointmentStatusValid, AppointmentStatus> {

    @Override
    public boolean isValid(AppointmentStatus value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(AppointmentStatus.values()).anyMatch(status -> status == value);
    }

}
