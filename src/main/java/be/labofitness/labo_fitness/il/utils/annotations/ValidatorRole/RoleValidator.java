package be.labofitness.labo_fitness.il.utils.annotations.ValidatorRole;

import be.labofitness.labo_fitness.dal.repository.RoleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class RoleValidator implements ConstraintValidator<CustomRoleValidator, String> {

    private final List<String> roleValid; //Arrays.asList("USER", "CLIENT", "PHYSIOTHERAPIST", "MODERATOR", "COACH", "ADMIN");
    private final RoleRepository repository;

    public RoleValidator(List<String> roleValid, RoleRepository repository) {
        this.repository = repository;
        this.roleValid = repository.findAllName();
    }

    @Override
    public void initialize(CustomRoleValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        if(role == null){
            return true;
        }
        return roleValid.contains(role);
    }
}
