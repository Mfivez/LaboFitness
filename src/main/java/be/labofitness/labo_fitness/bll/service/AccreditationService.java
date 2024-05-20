package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccreditationService extends CrudService<Accreditation, Long> {

    //BUG GITHUB
    Accreditation createWithParam(AccreditationType accreditationType, String description, Professional pro);

}
