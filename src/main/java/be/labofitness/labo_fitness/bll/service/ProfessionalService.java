package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.models.request.client.registerClient.ClientRegisterRequest;
import be.labofitness.labo_fitness.bll.models.request.professionnel.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.models.response.user.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalService extends CrudService<Professional, Long> {

    public RegisterResponse register(ProfessionalRegisterRequest request);

}
