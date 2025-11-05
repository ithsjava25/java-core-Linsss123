package com.example;

import java.time.LocalDate;

/**
 * Perishable representerar produkter som har ett utgångsdatum.
 * isExpired() använder expirationDate() (polymorfiskt) och hanterar null säkert.
 */
public interface Perishable {
    /**
     * Returnerar utgångsdatum för produkten (kan vara null för icke-perishables).
     */
    LocalDate expirationDate();

    /**
     * Standardmetod som avgör om produkten är utgången (true om expirationDate < idag).
     * Använder expirationDate() för att få datum från implementerande klass.
     */
    default boolean isExpired() {
        LocalDate exp = expirationDate();
        return exp != null && exp.isBefore(LocalDate.now());
    }
}
