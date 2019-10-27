package io.javafaktura.spring.boot.conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author Adam Król 2019-10-27
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnInternet.class)
public @interface ConditionalOnInternet {
}
