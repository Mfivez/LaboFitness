package be.labofitness.labo_fitness.il.utils.annotations.AccreditationValidor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = AccreditationValidator.class)
@Target(ElementType.FIELD)
public @interface AccreditationValid {

    String message() default "Gender must be a valid gender type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
