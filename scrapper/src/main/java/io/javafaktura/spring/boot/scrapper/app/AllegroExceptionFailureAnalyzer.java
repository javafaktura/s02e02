package io.javafaktura.spring.boot.scrapper.app;

import io.javafaktura.spring.boot.allegro.UnableToSetupAllegroException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author Adam Król 2019-10-27
 */
public class AllegroExceptionFailureAnalyzer extends AbstractFailureAnalyzer<UnableToSetupAllegroException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, UnableToSetupAllegroException cause) {
        return new FailureAnalysis("Allegro could not be initialized.", "Maybe turn on internet.", rootFailure);
    }
}
