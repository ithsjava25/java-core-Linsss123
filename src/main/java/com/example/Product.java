package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class Product {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private final LocalDate expiryDate; // kan vara null för icke-perishables
    private BigDecimal price;
    private final boolean perishable;
    private final boolean shippable;

    public Product(UUID uuid,
                   String name,
                   Category category,
                   BigDecimal price,
                   LocalDate expiryDate,
                   boolean perishable,
                   boolean shippable) {
        this.uuid = Objects.requireNonNull(uuid, "uuid cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.category = Objects.requireNonNull(category, "category cannot be null");
        this.price = Objects.requireNonNull(price, "price cannot be null");
        this.expiryDate = expiryDate;
        this.perishable = perishable;
        this.shippable = shippable;
    }

    /**
     * Subklasser ska returnera en beskrivande sträng, t.ex. "Food: Milk, Expires: 2025-12-24".
     */
    public abstract String productDetails();

    public UUID uuid() {
        return uuid;
    }

    /**
     * Sätter nytt pris (kan användas av Warehouse.updateProductPrice).
     */
    public void price(BigDecimal price) {
        this.price = Objects.requireNonNull(price, "price cannot be null");
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

    public LocalDate expiryDate() {
        return expiryDate;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public boolean isShippable() {
        return shippable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return uuid.equals(product.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
