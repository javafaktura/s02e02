package io.javafaktura.spring.boot.allegro;

/**
 * @author Adam Król 2019-10-27
 */
public class UnableToSetupAllegroException extends RuntimeException {

    UnableToSetupAllegroException(String message, Throwable cause) {
        super(message, cause);
    }
}
