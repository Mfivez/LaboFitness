package be.labofitness.labo_fitness.bll.model.admin.manageEmailStatus;

public record AdminManageEmailStatusRequest(

        String email,
        Boolean emailActive
) {
}
