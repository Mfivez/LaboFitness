package be.labofitness.labo_fitness.bll.models.request.client.makeAppointment;

public record MakeRequestForAppointmentRequest(
        String reasonOfAppointment,
        String physiotherapistEmail
) {
}