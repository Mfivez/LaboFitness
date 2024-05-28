package be.labofitness.labo_fitness.bll.model.coach.ManageEventInscription;

public record ManageEventInscriptionRequest(
    boolean state,
    Long id
) {
}
