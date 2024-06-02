package be.labofitness.labo_fitness.il.utils.annotations.appointmentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = AppointmentStatusValidator.class)
@Target(ElementType.FIELD)
public @interface AppointmentStatusValid {

    String message() default "Invalid month: Must be a number between 1 and 12 or a month's name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
