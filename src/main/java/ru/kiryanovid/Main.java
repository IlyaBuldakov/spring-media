package ru.kiryanovid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Главный класс приложения.
 */
@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
