package be.labofitness.labo_fitness.il.utils.annotations.genderValidator;
import be.labofitness.labo_fitness.domain.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class GenderValidator implements ConstraintValidator<GenderValid, Gender> {

    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(Gender.values()).anyMatch(gender -> gender == value);
    }

}

