package be.labofitness.labo_fitness.bll.model.user.getCoach;

public record GetCoachBySpecificationRequest(

        Long clientId,

        String email,

        String name,

        String lastname,

        Boolean isRemote,

        String specialization,

        Integer pricePerHourMin,

        Integer pricePerHourMax

) {
}
