package be.labofitness.labo_fitness.bll.models.response.physiotherapist.manageAccount.changePassword;

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
