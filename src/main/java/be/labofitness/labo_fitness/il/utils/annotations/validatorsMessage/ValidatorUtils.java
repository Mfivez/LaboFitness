package be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage;

import be.labofitness.labo_fitness.domain.entity.Client;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;

import java.lang.reflect.Field;

import static be.labofitness.labo_fitness.il.utils.annotations.validatorsMessage.CustomFieldValidator.getFieldNameWithAnnotation;

public class ValidatorUtils {

    /**
     * Static final Constant for the Role in the validators
     */
    public static final String CLIENT = "client field : ";
    public static final String COACH = "coach field : ";
    public static final String PHYSIOTHERAPISTS = "physiotherapist field : ";
    public static final String ADMIN = "admin field : ";

    /**
     * Static final Constant for the Message in the validators
     * */
    public static final String NOTBLANK = " can't be blank";
    public static final String NOTNULL = " can't be null";

    public static String validatorMessage(String entity, String field, int fieldMinValue){
        return entity + " " + field + " must be at least " + fieldMinValue +
                (fieldMinValue < 2 ? " character " : " characters ") + "long";
    }

//    private String getFieldName(ConstraintValidatorContext context) {
//        try {
//            // Utilisation de la réflexion pour obtenir le champ à partir du chemin de propriété dans le contexte
//            Field field = context. getRootBeanClass().getDeclaredField(context.getPropertyPath().toString());
//            return field.getName();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        // Si la réflexion échoue, on retourne le nom du champ spécifié dans l'annotation
//        return field; // fallback to the provided field name in annotation
//    }

    public static final String fieldNameWithNotBlank = getFieldNameWithAnnotation(Client.class, NotBlank.class);



}
