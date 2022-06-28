package finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Главный класс программы.
 */
@SpringBootApplication
@EnableAsync
@EnableJpaRepositories
public class Main extends AsyncConfigurerSupport {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}











