package io.javafaktura.spring.boot.allegro.model;

import io.javafaktura.spring.boot.scrapper.api.model.Product;

/**
 * @author Adam Król 2019-10-19
 */
public class AllegroProduct extends Product {

    public AllegroProduct(String name, double price, String city) {
        super(name, price, city);
    }
}
