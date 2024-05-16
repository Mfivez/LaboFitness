package be.labofitness.labo_fitness.il.utils.initializer;

import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.dal.repository.AppointmentRepository;
import be.labofitness.labo_fitness.dal.repository.ClientRepository;
import be.labofitness.labo_fitness.dal.repository.PhysiotherapistRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Appointment;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import be.labofitness.labo_fitness.il.utils.initializer.base.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Order(9)
public class AppointmentDataIni extends DataInitializer {


    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final PhysiotherapistRepository physiotherapistRepository;


    @Override
    public void run(String... args) throws Exception {
        super.run(args);


        if (appointmentRepository.count() == 0) {
            Client client = clientRepository.findById(5L).orElseThrow();
            Physiotherapist physiotherapist = physiotherapistRepository.findById(4L).orElseThrow();

            Appointment appointment1 = new Appointment();
            appointment1.setPrice(50);
            appointment1.setDate(LocalDateTime.now());
            appointment1.setClient(client);
            appointment1.setPhysiotherapist(physiotherapist);


            Appointment appointment2 = new Appointment();
            appointment2.setPrice(70);
            appointment2.setDate(LocalDateTime.now().plusDays(1));
            appointment2.setClient(client);
            appointment2.setPhysiotherapist(physiotherapist);

            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2);

        }
    }
}
