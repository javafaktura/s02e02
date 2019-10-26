package io.javafaktura.spring.boot.olx.model;

import io.javafaktura.spring.boot.scrapper.api.model.Product;

/**
 * @author Adam Król 2019-10-19
 */
public class OlxProduct extends Product {
    public OlxProduct(String name, double price, String city) {
        super(name, price, city);
    }
}
