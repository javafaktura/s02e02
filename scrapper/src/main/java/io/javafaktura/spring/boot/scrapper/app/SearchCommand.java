package io.javafaktura.spring.boot.scrapper.app;

import io.javafaktura.spring.boot.allegro.AllegroScrapper;
import io.javafaktura.spring.boot.scrapper.api.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Adam Król 2019-10-27
 */
@Component
public class SearchCommand implements CommandLineRunner {

    private final AllegroScrapper allegroScrapper;

    @Autowired
    public SearchCommand(AllegroScrapper allegroScrapper) {
        this.allegroScrapper = allegroScrapper;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> results = allegroScrapper.findByPhrase("telewizor");
        results.forEach(System.out::println);
        System.out.println(results.size());
    }
}
