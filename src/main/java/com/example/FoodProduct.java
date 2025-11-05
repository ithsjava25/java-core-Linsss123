package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {
    private final BigDecimal weight;

    public FoodProduct(UUID uuid, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(uuid, name, category, price, expirationDate, true, true);
        if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price cannot be negative.");
        if (weight.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Weight cannot be negative.");
        this.weight = weight;
    }

    @Override
    public LocalDate expirationDate() {
        return expiryDate();
    }

    @Override
    public boolean isExpired() {
        LocalDate exp = expirationDate();
        return exp != null && exp.isBefore(LocalDate.now());
    }

    @Override
    public Double weight() {
        return weight != null ? weight.doubleValue() : 0.0;
    }

    @Override
    public java.math.BigDecimal calculateShippingCost() {
        // Frakt = weight * 50
        return weight.multiply(new BigDecimal("50")).setScale(2, java.math.RoundingMode.HALF_UP);
    }

    @Override
    public String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate().toString();
    }
}