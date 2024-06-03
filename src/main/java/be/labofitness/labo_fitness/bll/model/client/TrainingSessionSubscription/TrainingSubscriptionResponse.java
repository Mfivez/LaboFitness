package be.labofitness.labo_fitness.bll.model.client.TrainingSessionSubscription;

/**
 * Represents the response model for subscribing to a training session.
 * <p>
 * This record encapsulates a message indicating the success of the subscription.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * String successMessage = "Subscription successful.";
 * TrainingSubscriptionResponse response = new TrainingSubscriptionResponse(successMessage);
 * }</pre>
 *
 * @param message A message indicating the success of the subscription.
 */
public record TrainingSubscriptionResponse(

        String message
)
{

    public static TrainingSubscriptionResponse fromEntity(String message){
        return new TrainingSubscriptionResponse(
                message
        );
    }

}
