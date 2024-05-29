package be.labofitness.labo_fitness.bll.exception.inscriptionClosed;
import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

/**
 * {@code EventCloseException} is a subclass of {@link LaboFitnessException}
 * that indicates an event is closed for new inscriptions.
 */
public class EventCloseException extends LaboFitnessException {

    /**
     * Constructs a new {@code EventCloseException} with the specified {@code detail message}.
     * The {@code status code} is set to {@code 403 (Forbidden)} by default.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public EventCloseException(String message) {
        super(message, 403);
    }

    /**
     * Constructs a new {@code EventCloseException} with the specified {@code detail message} and {@code status code}.
     *
     * @param message the {@code detail message} (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param status  the {@code HTTP status code} (which is saved for later retrieval
     *                by the {@link #getStatus()} method).
     */
    public EventCloseException(String message, int status) {
        super(message, status);
    }

    /**
     * Constructs a new {@code EventCloseException} with the specified {@code detail message}, {@code cause}, and {@code status code}.
     *
     * @param message the {@code detail message}.
     * @param cause   the cause of the {@code EventCloseException}.
     * @param status  the {@code HTTP status code}.
     */
    public EventCloseException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }
}