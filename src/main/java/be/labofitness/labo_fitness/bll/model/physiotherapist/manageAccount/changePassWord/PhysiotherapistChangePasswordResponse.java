package be.labofitness.labo_fitness.bll.model.physiotherapist.manageAccount.changePassWord;

import be.labofitness.labo_fitness.domain.entity.Physiotherapist;

public record PhysiotherapistChangePasswordResponse(

        Long id,
        String message

) {
    public static PhysiotherapistChangePasswordResponse fromEntity(Physiotherapist physiotherapist, String message){
        return new PhysiotherapistChangePasswordResponse(
                physiotherapist.getId(),
                message + "successfully done"
        );
    }
}
