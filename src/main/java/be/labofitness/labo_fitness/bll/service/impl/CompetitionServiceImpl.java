package be.labofitness.labo_fitness.bll.service.impl;

import be.labofitness.labo_fitness.bll.service.CompetitionService;
import be.labofitness.labo_fitness.dal.repository.CompetitionRepository;
import be.labofitness.labo_fitness.domain.entity.Client;
import be.labofitness.labo_fitness.domain.entity.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    // region UTILS METHODS

    @Override
    public Competition getCompetitionByCompetitionNameId(String name) {
        return competitionRepository.findByCompetitionNameId(name).orElseThrow(() -> new RuntimeException("Competition not found"));}

    // endregion

    // region CLASSIC CRUD

    @Override
    public Competition getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Competition> getAll() {
        return List.of();
    }

    @Override
    public Competition create(Competition entity) {
        return null;
    }

    @Override
    public Competition update(Competition entity) {
        return null;
    }

    @Override
    public Competition delete(Long aLong) {
        return null;
    }

    // endregion

}
