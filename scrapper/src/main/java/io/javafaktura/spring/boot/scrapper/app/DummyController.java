package io.javafaktura.spring.boot.scrapper.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adam Król 2019-10-27
 */
@RestController
public class DummyController {

    @GetMapping("/")
    public void nothingHere() {

    }

}
