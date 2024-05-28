package be.labofitness.labo_fitness.il.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * <p>Configuration class for the application.</p>
 * <p>It will replace property placeholders {@code (application.yml)} with
 * the ones in the {@code .env} file.</p>
 * <p>This class is annotated with {@code @Configuration} to indicate that it is
 * a source of bean definitions.</p>
 * <p>The {@code AppConfig} class is not instantiable, attempting to do so will result
 * in an {@link IllegalStateException}.</p>
 * @author faisal
 */
@Configuration
public class PropertiesPlaceholderConfig {

    /**
     * Bean definition for {@link PropertySourcesPlaceholderConfigurer}.
     * <br>This bean allows the application to use placeholders in property files.
     * The placeholders are replaced with the actual values from the {@code .env} file.
     *
     * @return a {@link PropertySourcesPlaceholderConfigurer} with the location of the {@code .env} file set.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        configurer.setLocation(new FileSystemResource(".env"));

        return configurer;
    }
}


