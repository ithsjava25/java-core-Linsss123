package com.example;

import java.math.BigDecimal;

public class Shippable {
    private final double weight;

    public Shippable(double weight) {
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public BigDecimal calculateShippingCost() {
        return BigDecimal.valueOf(weight);
    }
}
