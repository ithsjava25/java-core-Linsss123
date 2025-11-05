package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Product{
    String name;
    Category category;
    LocalDate expiryDate;
    BigDecimal price;
    boolean perishable;
    UUID uuid;
    boolean shippable;

    public Product(UUID uuid, String name, Category category, BigDecimal price, LocalDate expiryDate, boolean perishable, boolean shippable) {
        this.uuid = uuid;
        this.name = name;
        this.category = category;
        this.expiryDate = expiryDate;
        this.price = price;
        this.perishable = perishable;
        this.shippable = shippable;
    }

    public String productDetails() {
        return "";
    }

    public UUID uuid() {
        return this.uuid;
    }

    public void price(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal price() {
        return price;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }
}
