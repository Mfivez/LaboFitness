package be.labofitness.labo_fitness.bll.model.admin.manageAccountStatus;

public record AdminManageAccountStatusRequest(
        String email,
        Boolean isActive
){
}
