package be.labofitness.labo_fitness.il.utils.annotations.validatorPassword;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{5,}$", message = "password must be 5 characters 1 number, one upper case and one special symbol")
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface CustomPasswordValidator {
    String message() default "password must be 5 characters 1 number, one upper case and one special symbol";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
