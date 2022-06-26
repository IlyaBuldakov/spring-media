package com.htc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Конфигурация для Springfox (Swagger UI).
 */
@Configuration
public class SpringfoxConfig {
  /**
   * Создать генератор документации Swagger UI.
   *
   * @return Генератор документации.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
  }
}