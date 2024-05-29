package be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount;

import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

public record PhysiotherapistManageAccountResponse(
        String name,
        Long id,
        String message
) {
    public static PhysiotherapistManageAccountResponse fromEntity(Physiotherapist physiotherapist,String message){
        return new PhysiotherapistManageAccountResponse(
                physiotherapist.getName(),
                physiotherapist.getId(),
                message + "successfully done"
        );
    }
}
