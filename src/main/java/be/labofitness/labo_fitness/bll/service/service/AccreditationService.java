package be.labofitness.labo_fitness.bll.service.service;
import be.labofitness.labo_fitness.bll.service.base.CrudService;
import be.labofitness.labo_fitness.domain.entity.Accreditation;
import be.labofitness.labo_fitness.domain.entity.Professional;
import be.labofitness.labo_fitness.domain.enums.AccreditationType;

/**
 * Service interface for managing {@link Accreditation}.
 * <br>Extends {@link CrudService} for basic CRUD operations.
 */
public interface AccreditationService extends CrudService<Accreditation, Long> {

    // region accreditation

    /**
     * Creates an {@link Accreditation} with the provided parameters.
     *
     * @param accreditationType the type of {@link Accreditation}
     * @param description       the description of the {@link Accreditation}
     * @param pro               the {@link Professional} for whom the {@link Accreditation} is created
     */
    void createWithParam(AccreditationType accreditationType, String description, Professional pro);

    // endregion

}
