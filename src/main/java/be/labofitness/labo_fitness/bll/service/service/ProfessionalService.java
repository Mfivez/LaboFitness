package be.labofitness.labo_fitness.bll.service.service;

import be.labofitness.labo_fitness.bll.model.register.ProfessionalRegisterRequest;
import be.labofitness.labo_fitness.bll.model.register.RegisterResponse;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Professional;

public interface ProfessionalService extends CrudService<Professional, Long> {


    RegisterResponse register(ProfessionalRegisterRequest request);

}
