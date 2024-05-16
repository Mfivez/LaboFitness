package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.AppointmentRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentDataIni extends DataInitializer {


    private final AppointmentRepository appointmentRepository;

    public AppointmentDataIni(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (appointmentRepository.count() == 0) {
            Appointment appointment1 = new Appointment();
            appointment1.setPrice(50);
            appointment1.setDate(LocalDateTime.now());

            Appointment appointment2 = new Appointment();
            appointment2.setPrice(70);
            appointment2.setDate(LocalDateTime.now().plusDays(1));

            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2);
        }
    }
}
