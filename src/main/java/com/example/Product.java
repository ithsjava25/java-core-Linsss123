package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Product extends Perishable{
    String name;
    Category category;
    LocalDate expiryDate;
    BigDecimal price;
    boolean perishable;
    UUID uuid;
    boolean shippable;

    public Product(String name, Category category, LocalDate expiryDate, BigDecimal price, boolean perishable, UUID uuid, boolean shippable) {
        super(LocalDate.now());
        this.name = name;
        this.category = category;
        this.expiryDate = expiryDate;
        this.price = price;
        this.perishable = perishable;
        this.uuid = uuid;
        this.shippable = shippable;
    }

    public Product() {
        super(LocalDate.now());
    }

    public boolean productDetails() {
        return false;
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
