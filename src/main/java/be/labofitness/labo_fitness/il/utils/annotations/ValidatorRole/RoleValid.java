package be.labofitness.labo_fitness.il.utils.annotations.ValidatorRole;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
public @interface RoleValid {

    String message() default "not valid";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};

}
