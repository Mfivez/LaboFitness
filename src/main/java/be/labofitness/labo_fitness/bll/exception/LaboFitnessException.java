package be.labofitness.labo_fitness.bll.exception;
import be.labofitness.labo_fitness.LaboFitnessApplication;
import be.labofitness.labo_fitness.il.utils.CPrinter;
import lombok.Getter;

/**
 * {@code Custom exception} class for the {@link LaboFitnessApplication}.
 * This class extends {@link RuntimeException} and provides additional details
 * about the error, including a {@code status code} and a {@code customizable message}.
 */
@Getter
public class LaboFitnessException extends RuntimeException {

    /**
     * The {@code HTTP status code} associated with the {@link Exception}.
     */
    private final int status;

    /**
     * The {@code detailed message} or object associated with the {@link Exception}.
     */
    private final Object message;

    /**
     * Constructs a new {@link LaboFitnessException }with the specified {@code detail message}.
     * The {@code status code} is set to {@code 500 (Internal Server Error)} by default.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public LaboFitnessException(String message) {
        super(message);
        this.status = 500;
        this.message = message;
    }

    /**
     * Constructs a new {@link LaboFitnessException} with the specified {@code detail message} and {@code status code}.
     *
     * @param message the {@code detail message} (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param status  the {@code HTTP status code} (which is saved for later retrieval
     *                by the {@link #getStatus()} method).
     */
    public LaboFitnessException(String message, int status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    /**
     * Constructs a new {@link LaboFitnessException} with the specified {@code detail message} and {@code status code}.
     *
     * @param message the {@code detail message}.
     * @param cause the cause of the {@link LaboFitnessException}.
     * @param status the {@code HTTP status code}.
     */
    public LaboFitnessException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }

    /**
     * Returns the {@code custom message} associated with this {@code exception}.
     *
     * @return the {@code custom message}.
     */
    public Object getCustomMessage() {
        return message;
    }

    /**
     * Returns the {@code detail message string} of this {@code exception}.
     * This method overrides the {@code getMessage()} method of the {@link Throwable} class.
     *
     * @return the {@code detail message string}.
     */
    @Override
    public String getMessage() {
        return message.toString();
    }

    /**
     * Returns a {@code string representation} of this {@code exception}.
     * The string includes the {@code class name}, {@code method name}, {@code file name}, {@code line number}, and {@code message}.
     *
     * @return a string representation of this exception.
     */
    @Override
    public String toString() {
        StackTraceElement elem = this.getStackTrace()[0];
        return String.format(CPrinter.brightCyan("%s") + " thrown in " + CPrinter.brightPurple("%s") + "() at " + CPrinter.brightYellow("%s:%d")  + " with message: " + CPrinter.brightBlue("%s") ,
                this.getClass().getSimpleName(),
                elem.getMethodName(),
                elem.getFileName(),
                elem.getLineNumber(),
                this.getMessage());
    }

}
