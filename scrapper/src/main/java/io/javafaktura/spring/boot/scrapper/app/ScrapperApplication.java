package io.javafaktura.spring.boot.scrapper.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Adam Król 2019-10-19
 */
@SpringBootApplication
@ComponentScan("io.javafaktura.spring.boot") //Moved from ScrapperConfig
@PropertySource("classpath:/application.properties")
public class ScrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrapperApplication.class, args); //This can return context
    }
}
