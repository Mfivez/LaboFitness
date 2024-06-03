package be.labofitness.labo_fitness.dal.util;
import java.util.Optional;

public interface HasFindByMethod<T> {

    Optional<T> findByEmail(String mail);

}
