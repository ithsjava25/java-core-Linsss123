package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {
    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID uuid, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(uuid, name, category, price, null, false, true);
        if (warrantyMonths < 0) throw new IllegalArgumentException("Warranty months cannot be negative.");
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public Double weight() {
        return weight != null ? weight.doubleValue() : 0.0;
    }

    @Override
    public java.math.BigDecimal calculateShippingCost() {
        BigDecimal cost = new BigDecimal("79");
        if (weight != null && weight.compareTo(new BigDecimal("5.0")) > 0) {
            cost = cost.add(new BigDecimal("49"));
        }
        return cost.setScale(2, java.math.RoundingMode.HALF_UP);
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}