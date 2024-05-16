package be.labofitness.labo_fitness.domain.entity;

import be.labofitness.labo_fitness.domain.entity.base.BaseEntity;
import jakarta.persistence.Column;

public class Post extends BaseEntity<Long> {
    //TODO

    @Column(name = "name",unique = true, nullable = false)
    private String name;


}
