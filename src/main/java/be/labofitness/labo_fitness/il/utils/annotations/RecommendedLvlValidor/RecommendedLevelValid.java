package be.labofitness.labo_fitness.il.utils.annotations.RecommendedLvlValidor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = RecommendedLvlValidator.class)
@Target(ElementType.FIELD)
public @interface RecommendedLevelValid {

    String message() default "Gender must be a valid gender type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
