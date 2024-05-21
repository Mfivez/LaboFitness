package be.labofitness.labo_fitness.bll.exception.alreadyExists;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class EmailAlreadyExistsException extends LaboFitnessException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
