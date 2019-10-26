package io.javafaktura.spring.boot.scrapper.app;

import io.javafaktura.spring.boot.allegro.AllegroScrapper;
import io.javafaktura.spring.boot.scrapper.api.model.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author Adam Król 2019-10-19
 */
public class ScrapperApplication {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ScrapperConfig.class);

        var allegro = context.getBean("allegroScrapper", AllegroScrapper.class);
        List<Product> searchResult = allegro.findByPhrase("telewizor");

        searchResult.forEach(System.out::println);
        System.out.println(searchResult.size());
    }
}
