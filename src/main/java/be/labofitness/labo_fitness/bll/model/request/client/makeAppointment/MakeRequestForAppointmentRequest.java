package be.labofitness.labo_fitness.bll.model.request.client.makeAppointment;

public record MakeRequestForAppointmentRequest(
        String reasonOfAppointment,
        String physiotherapistEmail
) {
}