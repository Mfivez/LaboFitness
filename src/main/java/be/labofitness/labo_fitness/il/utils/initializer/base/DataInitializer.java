package be.labofitness.labo_fitness.il.utils.initializer.base;
import org.springframework.boot.CommandLineRunner;

/**
 * Base abstract class for {@link DataInitializer}.
 * <br> Data initializers are used to populate
 * the database with initial data when the application starts.
 */
public abstract class DataInitializer implements CommandLineRunner {

    /**
     * Runs the data initialization process.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {}

}
