package be.labofitness.labo_fitness.bll.models.request.physiotherapist.manageAccount.changePassWord;

import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.CustomPasswordValidator;

public record PhysiotherapistChangePasswordRequest(

        String oldPassword,

        @CustomPasswordValidator
        String newPassword
) {
}
