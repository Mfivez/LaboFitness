package be.labofitness.labo_fitness.il.utils.annotations.RecommendedLvlValidor;
import be.labofitness.labo_fitness.domain.enums.RecommendedLevel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RecommendedLvlValidator implements ConstraintValidator<RecommendedLevelValid, RecommendedLevel> {

    @Override
    public boolean isValid(RecommendedLevel value, ConstraintValidatorContext context) {
        if (value == null) {  return true;  }

        return Arrays.stream(RecommendedLevel.values()).anyMatch(recommendedLvlValid -> recommendedLvlValid == value);
    }

}

