package be.labofitness.labo_fitness.bll.model.coach.manageAccount.updateSpecificInformations;

public record CoachUpdateSpecificsInformationsRequest(

        boolean isRemote,
        int pricePerHour
) {
}
