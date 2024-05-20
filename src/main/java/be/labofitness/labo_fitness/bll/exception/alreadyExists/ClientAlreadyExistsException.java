package be.labofitness.labo_fitness.bll.exception.alreadyExists;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class ClientAlreadyExistsException extends LaboFitnessException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
