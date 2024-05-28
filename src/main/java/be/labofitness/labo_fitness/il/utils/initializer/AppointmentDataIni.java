package be.labofitness.labo_fitness.il.utils.initializer;
import be.labofitness.labo_fitness.dal.repository.AppointmentRepository;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.domain.enums.AppointmentStatus;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Data initializer for populating the database with initial {@link Appointment} data.
 */
@Component
@RequiredArgsConstructor
@Order(10)
public class AppointmentDataIni extends DataInitializer {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final PhysiotherapistRepository physiotherapistRepository;

    /**
     * Populates the database with initial {@link Appointment} data if no {@link Appointment} records exist.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        super.run(args);
        if (appointmentRepository.count() == 0) {
            Client client = clientRepository.findById(5L).orElseThrow();
            Physiotherapist physiotherapist = physiotherapistRepository.findById(3L).orElseThrow();

            Appointment appointment1 = new Appointment();
            appointment1.setPrice(50);
            appointment1.setStartDate(LocalDateTime.now());
            appointment1.setClient(client);
            appointment1.setAppointmentStatus(AppointmentStatus.ACCEPTED);
            appointment1.setPhysiotherapist(physiotherapist);
            appointment1.setReasonOfAppointment("mal");

            Appointment appointment2 = new Appointment();
            appointment2.setPrice(70);
            appointment2.setStartDate(LocalDateTime.now().plusDays(1));
            appointment2.setClient(client);
            appointment2.setPhysiotherapist(physiotherapist);
            appointment2.setReasonOfAppointment("mal");

            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2);
        }
    }
}
