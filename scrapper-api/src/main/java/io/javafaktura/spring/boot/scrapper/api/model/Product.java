package io.javafaktura.spring.boot.scrapper.api.model;

/**
 * @author Adam Król 2019-10-19
 */
public abstract class Product {

    private final String name;
    private final double price;
    private final String city;

    public Product(String name, double price, String city) {
        this.name = name;
        this.price = price;
        this.city = city;
    }

    @Override
    public String toString() {
        return name + " for " + price + " from " + city;
    }
}
