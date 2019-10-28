package io.javafaktura.spring.boot.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.springframework.boot.autoconfigure.condition.ConditionOutcome.match;
import static org.springframework.boot.autoconfigure.condition.ConditionOutcome.noMatch;

/**
 * @author Adam Król 2019-10-27
 */
public class OnInternet extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

        try {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("google.com", 80), 1000);
            }
            return match();
        } catch (IOException exception) {
            return noMatch("Host is not reachable");
        }
    }
}
