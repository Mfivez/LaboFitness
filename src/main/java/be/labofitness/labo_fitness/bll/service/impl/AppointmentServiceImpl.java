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
    public Appointment getOne(Long id) {
        return null;
    }

    /**
     * Retrieves all {@link Appointment}.
     *
     * @return a list of all {@link Appointment}
     */
    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    /**
     * Creates a new {@link Appointment}.
     *
     * @param entity the {@link Appointment} to create
     * @return the created {@link Appointment}
     */
    @Override
    public Appointment create(Appointment entity) {
        return appointmentRepository.save(entity);
    }

    /**
     * Updates an existing {@link Appointment}.
     *
     * @param entity the {@link Appointment} to update
     * @return the updated {@link Appointment}
     */
    @Override
    public Appointment update(Appointment entity) {
        return appointmentRepository.save(entity);
    }

    /**
     * Deletes an {@link Appointment} by its ID.
     *
     * @param id the ID of the {@link Appointment} to delete
     * @return the deleted {@link Appointment}, or null if not found
     */
    @Override
    public Appointment delete(Long id) {
        return null;
    }

    // endregion

    // region UTILS

    @Override
    public Long getPendingRequestsCount(Client client) {
        return appointmentRepository.countByClientAndAppointmentStatus(client, AppointmentStatus.PENDING);
    }

    @Override
    public boolean ExistByReason(Client client, String reason) {
        return appointmentRepository.existsByClientAndReasonOfAppointmentAndAppointmentStatus(client, reason, AppointmentStatus.PENDING);
    }

    // endregion

    // region SPECIFICATION

    public List<Appointment> findBySpecification(Specification<Appointment> specification) {
        return appointmentRepository.findAll(specification);
    }

    //endregion

}
