package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.AppointmentRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import org.springframework.stereotype.Component;

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
            Appointment a = new Appointment(
            );

            //appointmentRepository.save(a);
        }
    }

}
