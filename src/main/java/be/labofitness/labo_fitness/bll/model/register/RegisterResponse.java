package be.labofitness.labo_fitness.bll.model.register;

/**
 * Represents the response model for registration operations.
 * <p>
 * This record encapsulates a message indicating the success of the registration operation.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String registerSuccessMessage = "Registration successful!";
 *
 * RegisterResponse response = new RegisterResponse(registerSuccessMessage);
 * }</pre>
 *
 * @param registerSuccessMessage The message indicating the success of the registration operation.
 */
public record RegisterResponse(
        String registerSuccessMessage
        //todo send email to client

) {

    public static RegisterResponse fromEntity(String registerSuccessMessage){
        return new RegisterResponse(registerSuccessMessage);
    }

}
