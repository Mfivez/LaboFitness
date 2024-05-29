package be.labofitness.labo_fitness.bll.exception.notMatching;
import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

/**
 * {@code NotMatchingException} is a subclass of {@link LaboFitnessException}
 * that indicates a specific entity or condition does not match the expected criteria.
 */
public class NotMatchingException extends LaboFitnessException {

    /**
     * Constructs a new {@code NotMatchingException} with the specified {@code detail message}.
     * The {@code status code} is set to {@code 400 (Bad Request)} by default.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public NotMatchingException(String message) {
        super(message, 400);
    }

    /**
     * Constructs a new {@code NotMatchingException} with the specified {@code detail message} and {@code status code}.
     *
     * @param message the {@code detail message} (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param status  the {@code HTTP status code} (which is saved for later retrieval
     *                by the {@link #getStatus()} method).
     */
    public NotMatchingException(String message, int status) {
        super(message, status);
    }

    /**
     * Constructs a new {@code NotMatchingException} with the specified {@code detail message}, {@code cause}, and {@code status code}.
     *
     * @param message the {@code detail message}.
     * @param cause   the cause of the {@code NotMatchingException}.
     * @param status  the {@code HTTP status code}.
     */
    public NotMatchingException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }

}
