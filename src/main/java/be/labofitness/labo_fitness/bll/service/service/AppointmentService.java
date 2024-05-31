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
public interface AppointmentService extends CrudService<Appointment, Long> {

    /**
     * Retrieves the count of pending appointment requests for a {@link Client}.
     *
     * @param client the client for which to count the pending requests
     * @return the count of pending appointment requests for the client
     */
    Long getPendingRequestsCount(Client client);

    /**
     * Checks if there exists an appointment with the specified reason for a {@link Client}.
     *
     * @param client the client for which to check the existence of the appointment
     * @param reason the reason of the appointment to check for
     * @return {@code true} if an appointment with the specified reason exists for the client, {@code false} otherwise
     */
    boolean ExistByReason(Client client, String reason);

    /**
     * Finds appointments based on the provided specification.
     *
     * @param specification the specification to filter appointments
     * @return a list of appointments that match the provided specification
     */
    List<Appointment> findBySpecification(Specification<Appointment> specification);

}
