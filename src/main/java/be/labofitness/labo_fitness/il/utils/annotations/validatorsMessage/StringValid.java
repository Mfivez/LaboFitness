package be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Custom annotation} for specifying {@code validation constraints} with {@code custom error messages}.
 */
@Constraint(validatedBy = CustomFieldValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringValid {

    /**
     * Specifies the default error message for {@code validation failure}.
     *
     * @return The default error message.
     */
    String message() default "Invalid Field: ";

    /**
     * Specifies the {@code validation groups} targeted for this {@code constraint}.
     *
     * @return The {@code validation groups} targeted for this {@code constraint}.
     */
    Class<?> [] groups() default {};

    /**
     * Specifies the {@link Payload} type for this {@code constraint}.
     *
     * @return The {@link Payload} type for this {@code constraint}.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Specifies the {@code entity} associated with the {@code field} being validated.
     *
     * @return The {@code entity} associated with the {@code field} being validated.
     */
    String entity();

    /**
     * Specifies the {@code field} name being validated.
     *
     * @return The {@code field} name being validated.
     */
    String field();

    /**
     * Specifies the minimum value required for the {@code field}.
     *
     * @return The minimum value required for the {@code field}.
     */
    int min();



}
