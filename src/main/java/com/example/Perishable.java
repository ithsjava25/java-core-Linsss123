package com.example;

import java.time.LocalDate;

public interface Perishable {
    /**
     * Returnerar utgångsdatum för produkten.
     */
    LocalDate expirationDate();

    /**
     * Standardmetod som avgör om produkten är utgången (true om expirationDate < idag).
     */
    default boolean isExpired() {
        LocalDate exp = expirationDate();
        return exp != null && exp.isBefore(LocalDate.now());
    }
}
