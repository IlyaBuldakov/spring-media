package finalproject.configuration;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Конфигурация SpringFox.
 */
@Configuration
public class SpringFoxConfig {

  /**
   * Сборка Springfox.
   *
   * @return docket.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInform())
            .securitySchemes(Arrays.asList(apiKey()));
  }

  private ApiInfo apiInform() {
    return new ApiInfoBuilder()
            .title("Система создания медиаконтента")
            .version("1.0")
            .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("Bearer", "Authorization", "header");
  }


}