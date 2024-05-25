package be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomFieldValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatorMessageCustom {

    String message() default "Invalid Field";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String entity();

    String field();

    int min();
}
