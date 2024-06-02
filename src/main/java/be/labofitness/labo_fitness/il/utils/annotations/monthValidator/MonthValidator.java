package be.labofitness.labo_fitness.il.utils.annotations.monthValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Month;
import java.util.Arrays;

public class MonthValidator implements ConstraintValidator<MonthValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        try {
            int monthNumber = Integer.parseInt(value);
            if (monthNumber >= 1 && monthNumber <= 12) {  return true;  }
        }
        catch (NumberFormatException ignored) {}

        return Arrays.stream(Month.values())
                .anyMatch(month -> month.name().equalsIgnoreCase(value));
    }

}
