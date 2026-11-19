package globalsolution.citizentech.trilhamagica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "globalsolution.citizentech.trilhamagica.model")
@EnableJpaRepositories(basePackages = "globalsolution.citizentech.trilhamagica.repository")
public class TrilhamagicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrilhamagicaApplication.class, args);
    }
}