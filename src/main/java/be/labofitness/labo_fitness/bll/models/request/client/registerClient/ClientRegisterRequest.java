package be.labofitness.labo_fitness.bll.models.request.client.registerClient;

import be.labofitness.labo_fitness.domain.enums.Gender;
import jakarta.validation.constraints.Pattern;

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
