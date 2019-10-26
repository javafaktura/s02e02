package io.javafaktura.spring.boot.scrapper.api;

import io.javafaktura.spring.boot.scrapper.api.model.Product;

import java.util.List;

/**
 * @author Adam Król 2019-10-19
 */
public interface Scrapper {
    List<Product> findByPhrase(String phrase);
}
