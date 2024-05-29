package be.labofitness.labo_fitness.bll.exception.Exist;
import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

/**
 * {@code AlreadyExistException} is a subclass of {@link LaboFitnessException}
 * that indicates a specific entity already exists.
 */
public class AlreadyExistException extends LaboFitnessException {

    /**
     * Constructs a new {@code AlreadyExistException} with the specified {@code detail message}.
     * The {@code status code} is set to {@code 409 (Conflict)} by default.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public AlreadyExistException(String message) {
        super(message, 409);
    }

    /**
     * Constructs a new {@code AlreadyExistException} with the specified {@code detail message} and {@code status code}.
     *
     * @param message the {@code detail message} (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param status  the {@code HTTP status code} (which is saved for later retrieval
     *                by the {@link #getStatus()} method).
     */
    public AlreadyExistException(String message, int status) {
        super(message, status);
    }

    /**
     * Constructs a new {@code AlreadyExistException} with the specified {@code detail message}, {@code cause}, and {@code status code}.
     *
     * @param message the {@code detail message}.
     * @param cause the cause of the {@code AlreadyExistException}.
     * @param status the {@code HTTP status code}.
     */
    public AlreadyExistException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }
}