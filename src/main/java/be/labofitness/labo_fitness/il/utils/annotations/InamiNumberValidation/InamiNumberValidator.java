package be.labofitness.labo_fitness.il.utils.annotations.InamiNumberValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InamiNumberValidator implements ConstraintValidator<InamiValidator, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        String inamiValue = Long.toString(value);
        return inamiValue.length() == 11;
    }

}
