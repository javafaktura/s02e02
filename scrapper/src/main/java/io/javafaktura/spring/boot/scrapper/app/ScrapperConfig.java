package io.javafaktura.spring.boot.scrapper.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Adam Król 2019-10-19
 */
@ComponentScan("io.javafaktura.spring.boot")
@PropertySource("classpath:/application.properties")
class ScrapperConfig {
}
