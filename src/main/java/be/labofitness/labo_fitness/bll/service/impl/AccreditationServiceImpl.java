package be.labofitness.labo_fitness.bll.service.impl;
import be.labofitness.labo_fitness.bll.exception.Exist.DoesntExistException;
import be.labofitness.labo_fitness.bll.service.service.AccreditationService;
import be.labofitness.labo_fitness.dal.repository.AccreditationRepository;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link AccreditationService} interface.
 * <br>Provides methods to manage {@link Accreditation} entities.
 */
@RequiredArgsConstructor
@Service
public class AccreditationServiceImpl implements AccreditationService {

    private final AccreditationRepository accreditationRepository;

    /**
     * Creates a new {@link Accreditation} with specified parameters.
     *
     * @param accreditationType the type of the {@link Accreditation}
     * @param description       the description of the {@link Accreditation}
     * @param pro               the professional associated with the {@link Accreditation}
     * @return the created {@link Accreditation}
     */
    @Override
    public Accreditation createWithParam(AccreditationType accreditationType, String description, Professional pro) {
        Accreditation accreditation = new Accreditation();
        accreditation.setType(accreditationType);
        accreditation.setDescription(description);
        accreditation.setProfessional(pro);
        return accreditationRepository.save(accreditation);
    }

    // region CLASSIC CRUD

    /**
     * Retrieves an {@link Accreditation} by its ID.
     *
     * @param id the ID of the {@link Accreditation} to retrieve
     * @return the {@link Accreditation} with the given ID
     */
    @Override
    public Accreditation getOne(Long id) {
        return accreditationRepository.findById(id).orElseThrow(
                () -> new DoesntExistException("Accreditation with id " + id + " does not exist")
        );
    }

    /**
     * Retrieves all {@link Accreditation}.
     *
     * @return a list of all {@link Accreditation}
     */
    @Override
    public List<Accreditation> getAll() {
        return accreditationRepository.findAll();
    }

    /**
     * Creates a new {@link Accreditation}.
     *
     * @param entity the {@link Accreditation} to create
     * @return the created {@link Accreditation}
     */
    @Override
    public Accreditation create(Accreditation entity) {
        return accreditationRepository.save(entity);
    }

    /**
     * Updates an existing {@link Accreditation}.
     *
     * @param entity the {@link Accreditation} to update
     * @return the updated {@link Accreditation}
     */
    @Override
    public Accreditation update(Accreditation entity) {
        return accreditationRepository.save(entity);
    }

    /**
     * Deletes an {@link Accreditation} by its ID.
     *
     * @param id the ID of the {@link Accreditation} to delete
     * @return the deleted {@link Accreditation}, or null if not found
     */
    @Override
    public Accreditation delete(Long id) {
        return null;
    }

    // endregion

}