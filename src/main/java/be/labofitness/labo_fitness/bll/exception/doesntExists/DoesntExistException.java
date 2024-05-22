package be.labofitness.labo_fitness.bll.exception.doesntExists;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class DoesntExistException extends LaboFitnessException {
    public DoesntExistException(String message) {
        super(message);
    }
}