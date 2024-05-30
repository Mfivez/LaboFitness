package be.labofitness.labo_fitness.il.utils;


public class RegexUtils {

    /**
     * EMAILREGEX
     * source : {@code https://emailregex.com/index.html}
     */
    public final static String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";


    /**
     * PASSWORDREGEX :
     * This regex ensures that a {@code string is at least 5 characters long} and contains at least {@code one uppercase letter}, {@code one digit}, and {@code one special character (non-alphanumeric)}.
     */
    public final static String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{5,}$";
}
