package finalproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Arrays;

@Configuration
public class SpringFoxConfig {
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
            .description("work in progress")
            .termsOfServiceUrl("https://miro.medium.com/max/1400/1*ovB7iwrwZL8gqVzW0E1kNQ.jpeg")
            .version("1.0")
            .build();
  }
  private ApiKey apiKey() {
    return new ApiKey("Bearer", "Authorization", "header");
  }


}