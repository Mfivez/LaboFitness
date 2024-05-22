package be.labofitness.labo_fitness.bll.exception.Authentication;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class AuthenticationIsNullException extends LaboFitnessException {
    public AuthenticationIsNullException(String message) {
        super(message);
    }
}
