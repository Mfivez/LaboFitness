package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.service.service.AccreditationService;
import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccreditationServiceImpl implements AccreditationService {

    private final AccreditationRepository accreditationRepository;

    @Override
    public Accreditation getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Accreditation> getAll() {
        return List.of();
    }

    @Override
    public Accreditation create(Accreditation entity) {
        return null;
    }

    @Override
    public Accreditation createWithParam(AccreditationType accreditationType, String description, Professional pro) {
        Accreditation accreditation = new Accreditation();
        accreditation.setType(accreditationType);
        accreditation.setDescription(description);
        accreditation.setProfessional(pro);
        return accreditationRepository.save(accreditation);
    }

    @Override
    public Accreditation update(Accreditation entity) {
        return null;
    }

    @Override
    public Accreditation delete(Long aLong) {
        return null;
    }
}
