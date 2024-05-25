package be.labofitness.labo_fitness.bll.models.request.coach.manageAccount.changePassword;

import be.labofitness.labo_fitness.il.utils.annotations.validatorPassword.CustomPasswordValidator;

public record CoachChangePasswordRequest (

        String oldPassword,

        @CustomPasswordValidator
        String newPassword
) {

}
