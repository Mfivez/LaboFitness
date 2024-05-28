package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;

/**
 * Service interface for managing {@link Appointment}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface AppointmentService  extends CrudService<Appointment, Long> {
}
