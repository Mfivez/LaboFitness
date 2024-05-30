package be.labofitness.labo_fitness.il.utils.annotations.anyBooleanValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = AnyBooleanValidator.class)
@Target(ElementType.FIELD)
public @interface AnyBooleanValid {

    String message() default "Invalid AnyBoolean: Must be true, false or any";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
