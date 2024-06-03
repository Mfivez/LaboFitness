package be.labofitness.labo_fitness.bll.exception.Exist;
import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

/**
 * {@code DoesntExistException} is a subclass of {@link LaboFitnessException}
 * that indicates a specific entity does not exist.
 */
public class DoesntExistException extends LaboFitnessException {

    /**
     * Constructs a new {@code DoesntExistException} with the specified {@code detail message}.
     * The {@code status code} is set to {@code 404 (Not Found)} by default.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public DoesntExistException(String message) {
        super(message, 404);
    }

    /**
     * Constructs a new {@code DoesntExistException} with the specified {@code detail message} and {@code status code}.
     *
     * @param message the {@code detail message} (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param status  the {@code HTTP status code} (which is saved for later retrieval
     *                by the {@link #getStatus()} method).
     */
    public DoesntExistException(String message, int status) {
        super(message, status);
    }

    /**
     * Constructs a new {@code DoesntExistException} with the specified {@code detail message}, {@code cause}, and {@code status code}.
     *
     * @param message the {@code detail message}.
     * @param cause   the cause of the {@code DoesntExistException}.
     * @param status  the {@code HTTP status code}.
     */
    public DoesntExistException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }
}
