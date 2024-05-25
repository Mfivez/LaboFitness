package be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CustomFieldValidator implements ConstraintValidator<ValidatorMessageCustom, String> {

    private String entity;
    private String field;
    private int minimalValue;


    @Override
    public void initialize(ValidatorMessageCustom constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
        this.minimalValue = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if ( value == null){
            return false;
        }

        boolean isValid = value.length() >= minimalValue;

        if(!isValid){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    ValidatorUtils.validatorMessage(entity, field, minimalValue)
            ).addConstraintViolation();
        }

        return isValid;
    }

    public static String getFieldNameWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation)) {
                return field.getName();
            }
        }
        return null;
    }
}
