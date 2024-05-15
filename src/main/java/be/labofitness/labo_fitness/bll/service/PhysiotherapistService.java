package be.labofitness.labo_fitness.bll.service;

import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Physiotherapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysiotherapistService  extends CrudService<Physiotherapist, Long> {
}
