package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Service interface for managing {@link Appointment}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface AppointmentService  extends CrudService<Appointment, Long> {

    Long getPendingRequestsCount(Client client);

    boolean ExistByReason(Client client, String reason);

    List<Appointment> findBySpecification(Specification<Appointment> specification);

}
