package kg.rinat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket configureSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/v1/**"))
                .apis(RequestHandlerSelectors.basePackage("kg.rinat"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Counter API",
                "Приложение для управления счётчиками",
                "v1.0",
                "Бесплатная",
                new Contact("Ринат Муратидинов", "http://vk.com/muratidinov", "muratidinov@gmail.com"),
                "API LICENCE",
                "http://vk.com/muratidinov",
                new ArrayList<>()
        );
    }
}
