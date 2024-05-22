package be.labofitness.labo_fitness.bll.exception.Authentication;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class AuthenticationCastingException extends LaboFitnessException {
    public AuthenticationCastingException(String message) {
        super(message);
    }
}
