package be.labofitness.labo_fitness.api.controller.advisor;
import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller advice class for global exception handling in the application.
 * Uses {@code @ControllerAdvice} to capture and handle specific exceptions in a centralized manner.
 */
@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    /**
     * <p>Handles exceptions of type {@link LaboFitnessException}.</p>
     * <p>This method captures {@link LaboFitnessException}, logs the error message,
     * and returns an HTTP response with a status containing the error message.</p>
     *
     * @param error the {@link LaboFitnessException} thrown when the email already exists
     * @return a {@link ResponseEntity} containing the error message with a status
     */
    @ExceptionHandler(LaboFitnessException.class)
    public ResponseEntity<String> handleRuntimeException(LaboFitnessException error){
        log.error(error.toString());
        return ResponseEntity.status(error.getStatus()).body(error.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException thrown when validation on an argument annotated with {@code @Valid } fails.
     *
     *
     * <p>This method logs the exception and collects all validation errors into a map where the key is the field name
     * and the value is the default error message. If the default message is null, a generic error message is used.</p>
     *
     * @param error the {@link MethodArgumentNotValidException} containing validation errors
     * @return a {@link ResponseEntity} containing a map of field names and their corresponding error messages, with an HTTP status of 406 (Not Acceptable)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException error){
        log.error(error.toString());

        Map<String, String> errors = error.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError -> Optional.ofNullable(FieldError.getDefaultMessage()).orElse("Validator Error: DefaultMessage cannot be Null.")
                ));
        return ResponseEntity.status(406).body(errors);
    }

}
