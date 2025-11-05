package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Warehouse {
    private static final Map<String, Warehouse> INSTANCES = new ConcurrentHashMap<>();
    private final List<Product> products = new ArrayList<>();
    private final Set<Product> changedProducts = new HashSet<>();

    public static Warehouse getInstance(String testWarehouse) {
        return INSTANCES.computeIfAbsent(testWarehouse, k -> new Warehouse());
    }

    public void clearProducts() {
        products.clear();
        changedProducts.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        products.add(product);
        changedProducts.add(product);
    }

    public List<Product> getProducts() {
        // Return an unmodifiable copy to avoid test leakage
        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    public void remove(Product product) {
        products.remove(product);
        changedProducts.remove(product);
    }

    public void remove (UUID id) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        boolean removed = false;
        for (Product p : products) {
            if (p != null && id.equals(p.uuid())) {
                products.remove(p);
                changedProducts.remove(p);
                removed = true;
                break;
            }
        }
    }

    public List<Product> getProductsGroupedByCategories() {
        return List.of();
    }

    public void updateProductPrice(UUID nonExistentId, BigDecimal newPrice) {
        if (nonExistentId == null) throw new IllegalArgumentException("id cannot be null");
        boolean updated = false;
        for (Product p : products) {
            if (p != null && nonExistentId.equals(p.uuid())) {
                p.price(newPrice);
                changedProducts.add(p);
                updated = true;
                break;
            }
        }
        if (!updated) {
            throw new NoSuchElementException("Product not found with id: " + nonExistentId);
        }
    }

    public List<Product> getProductById(UUID uuid) {
        return List.of();
    }

    public List<Perishable> expiredProducts() {
        List<Perishable> expired = new ArrayList<>();
        for (Product p : products) {
            if (p instanceof Perishable per && per.isExpired()) {
                expired.add(per);
            }
        }
        return Collections.unmodifiableList(expired);
    }

    public List<Shippable> shippableProducts() {
        return null;
    }
}
