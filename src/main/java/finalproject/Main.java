package finalproject;

import finalproject.configuration.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Главный класс программы.
 */
@SpringBootApplication
@EnableAsync
@EnableJpaRepositories
@EnableConfigurationProperties(StorageProperties.class)
public class Main extends AsyncConfigurerSupport {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}











