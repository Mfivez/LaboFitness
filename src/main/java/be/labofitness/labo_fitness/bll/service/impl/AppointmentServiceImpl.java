package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.service.service.AppointmentService;
import be.labofitness.labo_fitness.dal.repository.AppointmentRepository;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of the {@link AppointmentService} interface.
 * <br>Provides CRUD operations for managing {@link Appointment} entities.
 */
@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Appointment} by its ID.
     *
     * @param id the ID of the {@link Appointment} to retrieve
     * @return the {@link Appointment} with the given ID
     */
    @Override
    public Appointment getOne(Long id) {return null;}

    /**
     * Retrieves all {@link Appointment}.
     *
     * @return a list of all {@link Appointment}
     */
    @Override
    public List<Appointment> getAll() {return appointmentRepository.findAll();}

    /**
     * Creates a new {@link Appointment}.
     *
     * @param entity the {@link Appointment} to create
     * @return the created {@link Appointment}
     */
    @Override
    public Appointment create(Appointment entity) {return appointmentRepository.save(entity);}

    /**
     * Updates an existing {@link Appointment}.
     *
     * @param entity the {@link Appointment} to update
     * @return the updated {@link Appointment}
     */
    @Override
    public Appointment update(Appointment entity) {return appointmentRepository.save(entity);}

    /**
     * Deletes an {@link Appointment} by its ID.
     *
     * @param id the ID of the {@link Appointment} to delete
     * @return the deleted {@link Appointment}, or null if not found
     */
    @Override
    public Appointment delete(Long id) {return null;}

    // endregion

    // region UTILS

    /**
     * Retrieves the count of pending appointment requests for a given client.
     *
     * @param client the client for whom to retrieve the count of pending appointment requests
     * @return the count of pending appointment requests for the given client
     */
    @Override
    public Long getPendingRequestsCount(Client client) {
        return appointmentRepository.countByClientAndAppointmentStatus(client, AppointmentStatus.PENDING);
    }

    /**
     * Checks if an appointment exists for a given client and reason with a pending status.
     *
     * @param client the client for whom to check if an appointment exists
     * @param reason the reason of the appointment
     * @return true if an appointment exists for the given client and reason with a pending status, otherwise false
     */
    @Override
    public boolean ExistByReason(Client client, String reason) {
        return appointmentRepository.existsByClientAndReasonOfAppointmentAndAppointmentStatus(client, reason, AppointmentStatus.PENDING);
    }

    // endregion

    // region SPECIFICATION

    /**
     * Finds appointments based on the provided specification.
     *
     * @param specification the specification to filter appointments
     * @return a list of appointments matching the specification
     */
    public List<Appointment> findBySpecification(Specification<Appointment> specification) {
        return appointmentRepository.findAll(specification);
    }

    //endregion

}
