package be.labofitness.labo_fitness.il.utils.annotations.InamiNumberValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = InamiNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
public @interface InamiValidator {

    String message() default "error.physiotherapist.inamiNumber.invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};

}
