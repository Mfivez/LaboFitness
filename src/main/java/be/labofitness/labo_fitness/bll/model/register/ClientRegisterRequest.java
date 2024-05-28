package be.labofitness.labo_fitness.bll.model.register;

import be.labofitness.labo_fitness.domain.enums.Gender;

import java.time.Month;


//TODO VALIDATORS
public record ClientRegisterRequest (
        String name,
        String lastName,
        int year,
        Month month,
        int day,
        String email,
        String password,
        Gender gender,
        String street,
        String number,
        String city,
        String zipCode,
        int weight,
        int height
) {



}
