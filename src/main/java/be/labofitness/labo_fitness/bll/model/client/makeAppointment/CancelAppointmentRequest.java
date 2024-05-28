package be.labofitness.labo_fitness.bll.model.client.makeAppointment;

import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;


public record CancelAppointmentRequest(
        Long id,
        AppointmentStatus status,
        boolean isCancel
) {
}
