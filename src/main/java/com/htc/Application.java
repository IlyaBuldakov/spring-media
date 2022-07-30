package com.htc;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.unit.DataSize;

/**
 * Основной класс приложения.
 */
@SpringBootApplication
@EnableAsync
@EnableJpaRepositories
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Настройка ограничения на размер входящих файлов.
   */
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize(DataSize.ofGigabytes(50));
    factory.setMaxRequestSize(DataSize.ofGigabytes(50));
    return factory.createMultipartConfig();
  }
}
