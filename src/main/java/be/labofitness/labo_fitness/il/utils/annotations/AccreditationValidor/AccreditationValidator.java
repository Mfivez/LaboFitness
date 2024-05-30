package be.labofitness.labo_fitness.il.utils.annotations.AccreditationValidor;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class AccreditationValidator implements ConstraintValidator<AccreditationValid, AccreditationType> {

    @Override
    public boolean isValid(AccreditationType value, ConstraintValidatorContext context) {
        if (value == null) {  return true;  }

        return Arrays.stream(AccreditationType.values()).anyMatch(accreditationType -> accreditationType == value);
    }

}

