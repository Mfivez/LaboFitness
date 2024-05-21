package be.labofitness.labo_fitness.bll.exception.doesntExists;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class EmailDoesntExistException extends LaboFitnessException {

    public EmailDoesntExistException(String message) {
        super(message);
    }
}
