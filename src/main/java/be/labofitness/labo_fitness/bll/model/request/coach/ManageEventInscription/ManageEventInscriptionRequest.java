package be.labofitness.labo_fitness.bll.model.request.coach.ManageEventInscription;

public record ManageEventInscriptionRequest(
    boolean state,
    Long id
) {
}
