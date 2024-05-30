package be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * {@code Custom field validator} for validating field values based on {@code constraints} provided in {@code annotations}.
 */
public class CustomFieldValidator implements ConstraintValidator<StringValid, String> {

    private String entity;
    private String field;
    private int minimalValue;

    /**
     * Initializes the validator with {@code constraint annotation parameters}.
     *
     * @param constraintAnnotation The annotation instance containing validation parameters.
     */
    @Override
    public void initialize(StringValid constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
        this.minimalValue = constraintAnnotation.min();
    }

    /**
     * Validates the field value against the {@code specified constraints}.
     *
     * @param value                     The value of the {@code field} to be validated.
     * @param cvc The context in which the {@code constraint} is evaluated.
     * @return                          True if the {@code field value} is valid, otherwise false.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {
        if ( value == null){  return true;  }

        boolean isValid = value.length() >= minimalValue;

        if(!isValid){
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate(
                    cvc.getDefaultConstraintMessageTemplate() + ValidatorUtils.validatorMessage(entity, field, minimalValue)
            ).addConstraintViolation();
        }

        return isValid;
    }

    /**
     * Retrieves the name of the {@code field} annotated with the {@code specified annotation}.
     *
     * @param clazz      The class containing the {@code field}.
     * @param annotation The annotation class.
     * @return           The name of the {@code field} annotated with the {@code specified annotation} annotation, or null if not found.
     */
    public static String getFieldNameWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation)) {
                return field.getName();
            }
        }
        return null;
    }
}
