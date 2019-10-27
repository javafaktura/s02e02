package io.javafaktura.spring.boot.olx;

import io.javafaktura.spring.boot.conditional.ConditionalOnInternet;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Adam Król 2019-10-27
 */
@Configuration
@ConditionalOnInternet
@ComponentScan
public class OlxAutoConfiguration {
    
}
