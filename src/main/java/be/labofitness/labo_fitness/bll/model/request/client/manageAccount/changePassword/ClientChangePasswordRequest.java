package be.labofitness.labo_fitness.bll.model.request.client.manageAccount.changePassword;

import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.CustomPasswordValidator;

public record ClientChangePasswordRequest (

        String oldPassword,

        @CustomPasswordValidator
        String newPassword
){
}
