package be.labofitness.labo_fitness.il.utils.annotations.anyBooleanValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AnyBooleanValidator implements ConstraintValidator<AnyBooleanValid, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        return s.equalsIgnoreCase("true") ||
                s.equalsIgnoreCase("false") ||
                s.equalsIgnoreCase("any");
    }

}
