package be.labofitness.labo_fitness.bll.model.client.makeAppointment;

public record MakeRequestForAppointmentRequest(
        String reasonOfAppointment,
        String physiotherapistEmail
) {
}