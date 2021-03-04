package kg.rinat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class CounterApp {

    public static void main(String[] args) {
        SpringApplication.run(CounterApp.class, args);
    }
}
