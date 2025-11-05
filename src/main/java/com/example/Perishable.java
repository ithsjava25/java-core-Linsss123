package com.example;

import java.time.LocalDate;

public class Perishable extends Shippable{
    private final LocalDate expiryDate;

    public Perishable(LocalDate expiryDate) {
        super(1);
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return false;
    }

    public LocalDate expirationDate() {
        return expiryDate;
    }
}
