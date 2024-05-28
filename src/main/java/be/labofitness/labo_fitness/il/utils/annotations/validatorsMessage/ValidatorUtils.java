package be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Role;
import jakarta.validation.constraints.NotBlank;

import static be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.CustomFieldValidator.getFieldNameWithAnnotation;

/**
 * Utility class providing helper methods for {@code validation messages}.
 */
public class ValidatorUtils {

    /**
     * Static final Constant for the {@link Role} in the {@code validators}
     */
    public static final String CLIENT = "client field : ";
    public static final String COACH = "coach field : ";
    public static final String PHYSIOTHERAPISTS = "physiotherapist field : ";
    public static final String ADMIN = "admin field : ";

    /**
     * Static final Constant for the Message in the {@code validators}
     * */
    public static final String NOTBLANK = " can't be blank";
    public static final String NOTNULL = " can't be null";

    /**
     * Generates a {@code validation message} based on the {@code entity}, {@code field}, and {@code minimum field value}.
     *
     * @param entity         The {@code entity} associated with the {@code field} being validated.
     * @param field          The name of the {@code field} being validated.
     * @param fieldMinValue  The minimum value required for the {@code field}.
     * @return               The generated {@code validation message}.
     */
    public static String validatorMessage(String entity, String field, int fieldMinValue){
        return entity + " " + field + " must be at least " + fieldMinValue +
                (fieldMinValue < 2 ? " character " : " characters ") + "long";
    }

    /**
     * Retrieves the name of a {@code field} annotated with {@code @NotBlank} in the {@code Client class}.
     */
    public static final String fieldNameWithNotBlank = getFieldNameWithAnnotation(Client.class, NotBlank.class);

}
