package be.labofitness.labo_fitness.bll.exception.alreadyExists;

import be.labofitness.labo_fitness.bll.exception.LaboFitnessException;

public class ProfessionalAlreadyExistsException  extends LaboFitnessException {
    public ProfessionalAlreadyExistsException(String message) {
        super(message);
    }
}