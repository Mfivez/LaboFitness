package be.labofitness.labo_fitness.bll.exception.notMatching;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class PasswordNotMatchingException extends LaboFitnessException {
    public PasswordNotMatchingException(String message) {
        super(message);
    }
}
