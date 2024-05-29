package be.labofitness.labo_fitness.bll.exception.Authentication;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

/**
 * {@code AuthenticationException} is a subclass of {@link LaboFitnessException}
 * that indicates an authentication-related issue.
 */
public class AuthenticationException extends LaboFitnessException {

    /**
     * Constructs a new {@code AuthenticationException} with the specified {@code detail message}.
     * The {@code status code} is set to {@code 401 (Unauthorized)} by default.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public AuthenticationException(String message) {
        super(message, 401);
    }

    /**
     * Constructs a new {@code AuthenticationException} with the specified {@code detail message} and {@code status code}.
     *
     * @param message the {@code detail message} (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param status  the {@code HTTP status code} (which is saved for later retrieval
     *                by the {@link #getStatus()} method).
     */
    public AuthenticationException(String message, int status) {
        super(message, status);
    }

    /**
     * Constructs a new {@code AuthenticationException} with the specified {@code detail message}, {@code cause}, and {@code status code}.
     *
     * @param message the {@code detail message}.
     * @param cause the cause of the {@code AuthenticationException}.
     * @param status the {@code HTTP status code}.
     */
    public AuthenticationException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }
}
