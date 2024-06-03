package be.labofitness.labo_fitness.bll.model.user.getPhysiotherapist;

public record GetPhysioBySpecificationRequest(

        Long clientId,

        String email,

        String name,

        String lastname,

        String specialization

) {
}
