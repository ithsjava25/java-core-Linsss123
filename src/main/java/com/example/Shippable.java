package com.example;

import java.math.BigDecimal;

/**
 * Shippable representerar produkter som kan skickas.
 * weight() returnerar en Double (kan vara null om okänt).
 * calculateShippingCost() returnerar BigDecimal så att summor kan reduceras med BigDecimal::add.
 */
public interface Shippable {
    /**
     * Vikt i kilogram som Double (eller null om okänt).
     */
    Double weight();

    /**
     * Beräknad fraktkostnad som BigDecimal.
     */
    BigDecimal calculateShippingCost();
}
