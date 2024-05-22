package be.labofitness.labo_fitness.bll.exception.alreadyExists;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class IdAlreadyExistException extends LaboFitnessException {
    public IdAlreadyExistException(String message) {
        super(message);
    }
}
