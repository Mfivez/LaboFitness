package be.labofitness.labo_fitness.il.utils.annotations.extendedEmailvalidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.*;

import static be.labofitness.labo_fitness.il.utils.RegexUtils.EMAILREGEX;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom email validator annotation for validating email format.
 */
@Email(message = "Email is not correct")
@Pattern(regexp = EMAILREGEX,
        message = "Email format is not correct")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface CustomEmailValidator{

    /**
     * Message to be displayed when validation fails.
     *
     * @return Error message for invalid email format.
     */
    String message() default "Please provide a valid email address";

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

