package be.labofitness.labo_fitness.il.utils.annotations.validatorPassword;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom password validator annotation for validating password format.
 */
@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{5,}$", message = "password must be 5 characters 1 number, one upper case and one special symbol")
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface CustomPasswordValidator {

    /**
     * Message to be displayed when validation fails.
     *
     * @return Error message for invalid password format.
     */
    String message() default "password must be 5 characters 1 number, one upper case and one special symbol";

    /**
     * Groups the constraint belongs to.
     *
     * @return Array of group classes.
     */
    Class<?>[] groups() default {};

    /**
     * Payload associated with the constraint.
     *
     * @return Array of payload classes.
     */
    Class<? extends Payload>[] payload() default {};
}
