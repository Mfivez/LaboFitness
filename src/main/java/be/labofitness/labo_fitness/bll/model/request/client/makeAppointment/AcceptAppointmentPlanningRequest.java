package be.labofitness.labo_fitness.bll.model.request.client.makeAppointment;

import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;


public record AcceptAppointmentPlanningRequest(
        Long id,
        AppointmentStatus status,
        String description
) {

}